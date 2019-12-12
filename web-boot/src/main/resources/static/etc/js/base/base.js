
// 获取Url参数
function getUrlParams(name, context) {
    const reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    context = context || window;
    let r = context.location.search.substr(1).match(reg);
    if(r != null) return decodeURI(r[2]);
    return null;
}


var ityConsole = {
    logLevel : Number(getUrlParams('logLevel', top) || 1) ,
    logDebug(msg) {
        this.consoleLog(msg, 1);
    },
    logInfo(msg){
        this.consoleLog(msg, 2);
    },
    logWarring(msg){
        this.consoleLog(msg, 3);
    },
    logError(msg){
        this.consoleLog(msg, 4);
    },
    consoleLog(msg, level) {
        if (level && level >= this.logLevel) {
            console.log(msg);
        }
    }
};

function geneRespsDecorator(resp) {
    if (!resp) {
        resp = {"Comment":"未获取到返回值", "Code": "null"};
    }
    ityConsole.logInfo('init resp : ');
    ityConsole.logInfo(resp);
    return {
        _resp : resp,
        getCode : function () {
            return this._resp.Code;
        },
        getContent : function () {
            return this._resp.Comment
        },
        isCode: function (code) {
            return this.getCode() === code;
        },
        isSuccess : function () {
            return this.getCode() && this.getCode().charAt(0) === '0';
        },
        isFailure : function () {
            return ! this.isSuccess();
        },
        success : function (fun) {
            if (this.isSuccess()) {
                fun();
            }
        },
        failure : function (fun) {
            if (this.isFailure()) {
                fun();
            }
        },
        msg : function (msg) {
            if (msg) {
                layer.msg(msg, {}, ()=>{});
            } else {
                layer.msg(this.getContent(), {}, ()=>{});
            }
        },
        msgIfFailure: function () {
            if (this.isFailure()) {
                ityConsole.logWarring(this._resp);
                ityConsole.logWarring('alert if failure');
                layer.msg(this.getContent(), {}, ()=>{})
            }
        }
    }
}


$.sendPostRequest = function(url, params, callbackFun, failureCallback, allCallback) {
    if (!url) {
        ityConsole.logWarring('url 为空');
        layer.msg('url 为空');
        return;
    }
    params = params || {};
    let load = layer.load(0);
    ityConsole.logDebug('sendPostRequest -> %c : ' + url, 'color:blue');
    ityConsole.logDebug(params);
    $.ajax({
        url : url,
        type: "POST",
        data: params,
        dataType : "json",
        success : function (result, status){
            layer.close(load);
            ityConsole.logDebug('sendPostRequest -> %c response : ', 'color:blue');
            ityConsole.logDebug(result);
            if (! result) {
                layer.msg("后台传值发生错误");
            }
            let info = result.status;

            if (!info){
                layer.msg('系统错误!');
            }

            if (info.code.charAt(0) === '0'){
                if (typeof callbackFun === "function"){
                    callbackFun(result.data || {}, info);
                } else if (callbackFun === true){
                    layer.msg(info.text);
                }
            } else{
                if (typeof failureCallback === "function"){
                    failureCallback(result.data || {}, info);
                } else if (failureCallback === true){
                    layer.msg(info.text);
                }
            }
            if (typeof allCallback === "function") {
                allCallback();
            }
        },
        // XMLHttpRequest 对象，错误信息，（可能）捕获的错误对象。
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            layer.close(load);
            layer.msg("请求异常");
            ityConsole.logError(XMLHttpRequest);
            ityConsole.logError(textStatus);
            ityConsole.logError(errorThrown);
        }
    });
};

/*
*功能： 模拟form表单的提交
*参数： URL 跳转地址 PARAMTERS 参数
*/
$.sendFormRequest = function(url, params, docContext) {
    let context = docContext || document;
    //创建form表单
    let temp_form = context.createElement("form");
    temp_form.action = url;
    //如需打开新窗口，form的target属性要设置为'_blank'
    temp_form.target = "_self";
    temp_form.style = "display : none;";
    temp_form.method = "post";
    // temp_forfavicon.icom.style.display = "none";
    //添加参数
    for (let key in params) {
        let opt = context.createElement("textarea");
        opt.name = key;
        opt.value = params[key];
        temp_form.appendChild(opt);
    }
    context.body.appendChild(temp_form);
    //提交数据
    temp_form.submit();
};


/*
*功能： 模拟form表单的提交
*参数： URL 跳转地址 PARAMTERS 参数
*/
$.downloadFile = function(url, params, docContext) {
    let context = docContext || document;
    //创建form表单
    let ele = context.createElement("a");
    ele.setAttribute('href', url); //设置下载文件的url地址
    ele.setAttribute('download' , 'download'); //用于设置下载文件的文件名
    ele.click();
};


// 加载 js 资源
function loadHtml(url, id) {
    id = id || 'loadHtml_' + url.replace(/[\\\-/\.]/g,"_");
    if ($('#' + id).length) {
        ityConsole.logWarring('loadHtml repeat !!!, id : ' + id + ', url : ' + url);
    }
    let domScript = document.createElement('div');
    domScript.id = id;
    $(domScript).load(url);
    document.getElementsByTagName('body')[0].appendChild(domScript);
    return {
        html : $(domScript)
    }
}


var ityDM = {
    clearParameterCache : function () {
        $.sendPostRequest('debug/config/clearParameterCache');
    },
    toHtml : function (path) {
        if (path) {
            $.sendPostRequest('debug/toHtml/' + path);
        } else {
            ityConsole.logWarring('path is null');
        }
    },
    redirect : function (path) {
        if (path) {
            $.sendPostRequest('debug/redirect', {p: path});
        } else {
            ityConsole.logWarring('path is null');
        }
    },
    deleteAccount : function (phone) {
        if (phone) {
            $.sendPostRequest('debug/config/clearParameterCache', {phone : phone});
        } else {
            ityConsole.logWarring('phone is null');
        }
    }
};






























