// 应用前缀
const appSendPrefix = "/app";
// 一个订阅30秒没有使用则取消订阅
const maxNoCustomSubConnTime = 10000;


function ItyWebSocket(userGuid, instGuid) {
    if (!userGuid) {
        console.log("userGuid 为空, 无法创建 webSocket 连接");
        return;
    }
    // 用户guid
    let logFlag = false;
    let stompClient = null;
    let info = {userGuid: userGuid, instGuid : instGuid};

    let subscribePool = {
        pool :{
            // destination : {  // 通过目的地过滤
            //     subsType : { // 通过类型过滤
            //         pageId : callback
            //     },
            //     subsHandler : { // 订阅的返回值}
            // }
        },
        // 获取子订阅连接
        getSubConn : function(_destination){
            return this.pool[_destination];
        },
        // 获取子订阅连接, 如果连接不存在, 则初始化子订阅连接空对象
        getSetSubConn : function(_destination){
            let subsConn = this.getSubConn(_destination);
            // 如果订阅地址不存在, 则向后台发送订阅
            if (!subsConn){
                subsConn = {};
                // 加到 pool 里面
                this.pool[_destination] = subsConn;
            }
            return subsConn;
        },
        // 判断是否有相应子订阅连接
        hasSubConn : function(_destination){
            return Boolean(this.getSubConn(_destination));
        },
        // 判断是否有相应子订阅连接
        removeSubConn : function(_destination){
            delete this.pool[_destination];
        },
        // 设置子订阅连接
        setSubConn : function(_destination, subsHandler){
            let subsConn = this.getSetSubConn(_destination);
            subsConn.subsHandler = subsHandler;
        },
        // 向子订阅连接里添加订阅信息, 并返回移除函数
        addSubscribe : function(_destination, _subsType, _pageId, callback) {
            // 获取目的地的操作池
            let subsConn = this.getSetSubConn(_destination);
            let subsType = subsConn[_subsType];
            // 如果订阅类型不存在, 则创建订阅类型
            if (!subsType){
                subsType = {};
                subsConn[_subsType] = subsType;
            }
            // 如果相关页面不存在
            if (!subsType[_pageId]){
                subsType[_pageId] = callback;
                consoleLog('addSubs success', _destination, _subsType, _pageId);
            } else {
                subsType[_pageId] = callback;
                consoleLog('addSubs cover success', _destination, _subsType, _pageId);
            }
            let _this = this;
            // 创建返回 handler
            return {
                unsubscribe: function() {
                    _this.removeAndUnsubscribe(_destination, _subsType, _pageId);
                }
            };
        },
        removeAndUnsubscribe : function(_destination, _subsType, _pageId) {
            // 获取目的地的操作池
            let subsConn = this.getSubConn(_destination) || {};
            let type = subsConn[_subsType];
            if (!type) {
                consoleLog('not found type', _destination, _subsType, _pageId);
                return;
            }
            if (type[_pageId]) {
                delete type[_pageId];
                consoleLog('remove success', _destination, _subsType, _pageId);
            } else {
                consoleLog('remove not found', _destination, _subsType, _pageId);
            }
            // 判断type 是否有意义, 若对象为空则移除
            if (Object.getOwnPropertyNames(type).length === 0){
                delete subsConn[_subsType];
                consoleLog('remove by type', _destination, _subsType, _pageId);
            }
            // 判断type 是否有意义, 若对象为空则移除
            if (Object.getOwnPropertyNames(subsConn).length === 1 && subsConn.subsHandler){
                this.unsubscribe(_destination);
            }
        },
        getTypePageObj : function(_destination, _subsType){
            let subConn = this.getSubConn(_destination) || {};
            return subConn[_subsType] || {};
        },
        // 取消订阅
        unsubscribe : function(_destination) {
            // 如果
            let _this = this;
            setTimeout(function () {
                let subsConn = _this.getSubConn(_destination);
                if (subsConn && Object.getOwnPropertyNames(subsConn).length === 1 && subsConn.subsHandler){
                    subsConn.subsHandler.unsubscribe();
                    _this.removeSubConn(_destination);
                    consoleLog('unsubscribe success, %c==> ' + _destination);
                }
            }, maxNoCustomSubConnTime);
        },
        // 获取连接的目标数组
        getDestinations : function () {
            return Object.getOwnPropertyNames(this.pool);
        }
    };


    let pageSubsHandler = {
        pool : {
            // pageId : {
            //     type(_destination + _subsType) : handler
            // }
        },
        getPageId(_pageId){
            return this.pool[_pageId];
        },
        setPageId(_pageId, obj){
            this.pool[_pageId] = obj;
        },
        removePageId : function(_pageId){
            delete this.pool[_pageId];
        },
        addSubsHandler : function(_pageId, _destination, _subsType, _handler) {
            this.setPageId(_pageId, this.getPageId(_pageId) || {});
            this.getPageId(_pageId)[_destination + _subsType] = _handler;
            consoleLog('pageSubsHandler : ' + _pageId + ' --> ' + _subsType + ' addHandler!');
        },
        unSubscribeByPageId : function(_pageId) {
            let pageHandler = this.getPageId(_pageId);
            if (! pageHandler) {
                console.log('not found subscribe handler ==> ' + _pageId);
                return false;
            }
            this.removePageId(_pageId);
            for (let type in pageHandler) {
                pageHandler[type].unsubscribe();
                consoleLog('page : ' + _pageId + ' --> ' + type + ' unsubscribe!');
            }
            consoleLog('page : ' + _pageId + ' un subscribe finish!!!');
            return true;
        }
    };

    // 连接 webSocket
    function createAndConnect() {
        let socket = new SockJS('/socket');
        stompClient = Stomp.over(socket);
        // heart-beating 是默认启用的。heart-beating也就是频率，incoming是接收频率，outgoing是发送频率。
        stompClient.heartbeat.incoming = 0;
        // 默认 10000 毫秒
        stompClient.heartbeat.outgoing = 10000;
        stompClient.connect(info, function (frame) {
            console.log('webSocket connect success!');
            console.log(frame);
            // 如果是发生故障, 重新连接, 连接之后重连之前的子订阅
            let destinations = subscribePool.getDestinations();
            destinations.forEach(_destination => {
                console.log(subscribePool.getSubConn(_destination));
                let subsHandler = doSubscribe(_destination);
                subscribePool.setSubConn(_destination, subsHandler);
            });
        }, function (error) {
            console.log('webSocket connect error: ' + error);
            disconnect();
            console.log('webSocket reconnect start!');
            // 重新连接
            createAndConnect();
        });
    }

    // 断开连接
    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect(function () {
                console.log("webSocket disconnect!");
            });
        }
    }

    // 实际订阅操作
    function doSubscribe(_destination){
        return stompClient.subscribe(_destination, function(greeting){
            console.log(_destination);
            console.log(greeting);
            // 解析json
            let data = eval('(' + greeting.body + ')');
            // 获取订阅的目的地对象
            let funcObj = subscribePool.getTypePageObj(_destination, data.type);
            // 获取订阅的类型列表
            if (Object.getOwnPropertyNames(funcObj).length === 0){
                consoleLog('not found custom', _destination, data.type);
                return;
            }
            // 类型里面是 页面ID : 函数 形式, 循环执行相应函数
            for (let i in funcObj) {
                funcObj[i](data.data);
            }
        });
    }

    // 订阅
    function subscribe(_destination, _subsType, _pageId, callback) {
        // 如果订阅地址不存在, 则向后台发送订阅
        if (!subscribePool.hasSubConn(_destination)){
            // 订阅
            let subsHandler = doSubscribe(_destination);
            subscribePool.setSubConn(_destination, subsHandler);
        }
        let handler = subscribePool.addSubscribe(_destination, _subsType, _pageId, callback);
        // 加到 pageSubsHandler 中
        pageSubsHandler.addSubsHandler(_pageId, _destination, _subsType, handler);
        return handler;
    }
    // 打印个性化日志
    function consoleLog(redTest, _destination, _subsType, _pageId){
        if (logFlag) {
            console.log('%cws ' + redTest + '%c==> ' + _destination + ' -- ' + _subsType + ' -- ' + _pageId, 'color:red', '');
        }
    }
    // 发送 webSocket 消息
    function sendWebSocketMsg(destination, headers, body) {
        if (stompClient.connected) {
            headers = headers || {};
            headers.type = 'text';
            body = body || {};
            body.userGuid = info.userGuid;
            body = JSON.stringify({
                'userId': $("#send_userId").val(),
                'content': $("#send_content").val()
            });
            stompClient.send(appSendPrefix + destination, headers, body);
        } else {
            disconnect();
        }
    }
    return {
        _info : info,
        connect : createAndConnect,
        // 断开socket连接
        disconnect : disconnect,
        sendToAll : function(headers, body) {
            sendWebSocketMsg("/sendToAll", headers, body);
        },
        sendToUser : function(userGuid, headers, body) {
            sendWebSocketMsg("/sendToUser/" + userGuid, headers, body);
        },
        sendToMarket : function(marketGuid, headers, body) {
            sendWebSocketMsg("/sendToMarket/" + marketGuid, headers, body);
        },
        // 订阅系统广播
        subscribeAll : function(fun) {
            subscribe('/topic/message', fun);
        },
        // 订阅一对一通信
        subscribeSelf : function (_subsType, _pageId, callback) {
            if (!judgementIsArray(_subsType)) {
                _subsType = [_subsType];
            }
            return _subsType.map(it => subscribe('/user/' + userGuid + '/message', it, _pageId, callback));
        },
        // 订阅机构通信
        subscribeInstitution : function(_subsType, _pageId, callback) {
            if (!judgementIsArray(_subsType)) {
                _subsType = [_subsType];
            }
            return _subsType.map(it => subscribe('/topic/institution/' + info.instGuid, it, _pageId, callback));
        },
        // 订阅小市场通信
        subscribeMarket : function(marketIds, _subsType, _pageId, callback) {
            // 返回关闭的句柄
            if (judgementIsArray(marketIds)){
                return marketIds.map(marketId => subscribe('/topic/market/' + marketId, _subsType, _pageId, callback));
            }
            return [];
        },
        unSubscribeByPageId : function (_pageId) {
            return pageSubsHandler.unSubscribeByPageId(_pageId);
        },
        // 测试用
        getSubscribePool : function () {
            return subscribePool;
        },
        // 测试用
        getSubsHandlerPool : function () {
            return pageSubsHandler;
        },
        // 测试用
        setLogFlag : function (flag) {
            logFlag = flag;
        }
    }
}
