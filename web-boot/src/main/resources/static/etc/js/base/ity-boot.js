
// 加载 js 资源
// function loadJs(url, success) {
//     // check
//     if (typeof url !== "string" || url.constructor !== String){
//         console.log('url: ' + url + ' is not string');
//         return false;
//     }
//     // 防止重复加载
//     let repeatFlag = url.replace(/[\\\-/\.]/g,"_");
//     window.jsloaded = window.jsloaded || {};
//     if (window.jsloaded[repeatFlag]){
//         console.log('已经加载过js : ' + url);
//         return false;
//     }
//     window.jsloaded[repeatFlag] = true;
//     // 创建dom对象
//     let domScript = document.createElement('script');
//     domScript.type = "text/javascript";
//     domScript.src = url;
//     success = success || function () {};
//     domScript.onload = domScript.onreadystatechange = function () {
//         if (!this.readyState || 'loaded' === this.readyState || 'complete' === this.readyState) {
//             success();
//             this.onload = this.onreadystatechange = null;
//             this.parentNode.removeChild(this);
//         }
//     };
//     document.getElementsByTagName('head')[0].appendChild(domScript);
//     return true;
// }

// 加载 js 资源
function loadJs(url, success) {
    let domScript = document.createElement('script');
    domScript.type = "text/javascript";
    domScript.src = url;
    success = success || function () {};
    domScript.onload = domScript.onreadystatechange = function () {
        if (!this.readyState || 'loaded' === this.readyState || 'complete' === this.readyState) {
            success();
            this.onload = this.onreadystatechange = null;
            this.parentNode.removeChild(this);
        }
    }
    document.getElementsByTagName('head')[0].appendChild(domScript);
}




// 加载base.js
loadJs('static/etc/js/plugins/layer/layer.js');
loadJs('static/etc/js/plugins/bootstrap/bootstrap.js');
loadJs('static/etc/js/base/base.js');
loadJs('static/etc/js/base/ity-common-utils.js');

