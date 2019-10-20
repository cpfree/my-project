
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
// loadJs('etc/js/plugins/bootstrap/bootstrap.js');
// loadJs('etc/frame/jquery/jquery-current.min.js');
// loadJs('etc/js/vue.js');
loadJs('/etc/js/base/base.js');
loadJs('/etc/js/base/ity-common-utils.js');
loadJs('/etc/js/plugins/layer/layer.js');

