//V2 static
String.format = function () {
    if (arguments.length == 0) {
        return null;
    }
    let str = arguments[0];
    for (let i = 1; i < arguments.length; i++) {
        let re = new RegExp('\\{' + i + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};
// js小数运算出现多位小数如何解决

function getDecimalDigitalNumber(num){
    if (!num) {
        return 0;
    }
    try {
        let s = num.toString();
        if (s.includes('.')) {
            return s.split(".")[1].length;
        }
        return 0;
    } catch (e) {
        console.error(num);
    }
}

// 精准加法
function accAdd(arg1, arg2) {
    let r1 = getDecimalDigitalNumber(arg1);
    let r2 = getDecimalDigitalNumber(arg2);
    let m = Math.pow(10, Math.max(r1, r2));
    return (arg1 * m + arg2 * m) / m;
}

// 精准乘法
function accMul(arg1, arg2) {
    let m = 0;
    m += getDecimalDigitalNumber(arg1);
    m += getDecimalDigitalNumber(arg2);
    let s1 = Number(arg1.toString().replace(".", ""));
    let s2 = Number(arg2.toString().replace(".", ""));
    return s1 * s2 / Math.pow(10, m);
}

// 精准除法
function accDiv(arg1, arg2) {
    let t1 = getDecimalDigitalNumber(arg1);
    let t2 = getDecimalDigitalNumber(arg2);
    with (Math) {
        let r1 = Number(arg1.toString().replace(".", ""));
        let r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
}

Number.prototype.add = function (arg) {
    return accAdd(arg, this);
};

Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};

Number.prototype.div = function (arg) {
    return accDiv(this, arg);
};


function judgementIsString(obj) {
    return typeof obj === "string" && obj.constructor === String;
}

function judgementIsInteger(num) {
    return /(^[1-9]\d*$)/.test(num);
}

function judgementIsArray(value) {
    if (typeof Array.isArray === "function") {
        return Array.isArray(value);
    } else {
        return Object.prototype.toString.call(value) === "[object Array]";
    }
}


function judgeBrowser() {
    let userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    //判断是否Firefox浏览器
    if (userAgent.indexOf("Firefox") > -1) {
        return true;
    }
    //判断是否chorme浏览器
    if (userAgent.indexOf("Chrome") > -1) {
        return true;
    }
    return false;
    // //判断是否Opera浏览器
    // if (userAgent.indexOf("Opera") > -1) {
    //     return "Opera"
    // };
    // //判断是否Safari浏览器
    // if (userAgent.indexOf("Safari") > -1) {
    //     return "Safari";
    // }
    // //判断是否IE浏览器
    // if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
    //     return "IE";
    // }
    // //判断是否Edge浏览器
    // if (userAgent.indexOf("Trident") > -1) {
    //     return "Edge";
    // };
}

// var a = "I Love {0}, and You Love {1},Where are {0}! {4}";
// alert(String.format(a, "You", "Me"));
//
// alert(a.format("You", "Me"));


// 倒计时
// hideDiv  和 secondTextDiv 选择器, 仅仅支持jquery选择器的第一个元素
function disableForAWhile(hideDiv, secondTextDiv, time, str, style) {
    // 备份
    let tmpHtml = hideDiv[0].outerHTML;
    let secondHtml = secondTextDiv[0].outerHTML;
    time = time || 60;
    str = str || '重新发送({1})';
    style = style || {'pointer-events': 'none', 'cursor': 'not-allowed'};
    hideDiv.css(style);
    // clear function
    secondTextDiv.text(String.format(str, time--));
    let clear = function (id) {
        clearInterval(id);
        hideDiv[0].outerHTML = tmpHtml;
        secondTextDiv[0].outerHTML = secondHtml;
    };
    let innerId = setInterval(() => {
        secondTextDiv.text(String.format(str, time--));
        if (time <= 0) {
            clear(innerId);
        }
    }, 1000);
    return {
        clear: () => {
            clear(innerId)
        }
    };
}

Date.prototype.format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


function showCommonConfirm(vueContext, title, tip, success) {
    success = success || function () {
        console.log('未执行操作');
    };
    vueContext.$confirm(tip, title, {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(success).catch(() => {
        console.log('已取消操作');
    });
}


function renderCodeItemMulti(codeItems, code, splitSymbol, joinSymbol) {
    splitSymbol = splitSymbol || ',';
    joinSymbol = joinSymbol || ',';
    let text = [];
    let split = code.split(splitSymbol);
    split.forEach(it => {
        text.push(renderCodeItem(codeItems, it));
    });
    return text.join(joinSymbol)
}

function renderCodeItem(codeItems, code) {
    if (!judgementIsArray(codeItems)) {
        return '';
    }
    let find = codeItems.find(it => it.code === code);
    return find ? find.text : '';
}

function ityConfirm(content, options, yesFun, cancelFun) {
    options = options || {btn: ['确定','取消']};
    layer.confirm(content, options, (idx)=>{
        layer.close(idx);
        yesFun();
    }, (idx) => {
        layer.close(idx);
        if (cancelFun) {
            cancelFun();
        }
    });
}


// 数据字典工具
const nullCodeItem = {code : '', text: ''};

var iCodeItem = {
    getTextByCode: function (codeItem, code) {
        let find = codeItem.find(it => it.code === code);
        return find ? find.text : '';
    },
    findItem(codeItemArr, code) {
        let find = codeItemArr.find(it => it.code === code);
        return find || nullCodeItem;
    },
    findTextFromArr(codeItemArr, code) {
        return this.findItem(codeItemArr, code).text;
    }
};
