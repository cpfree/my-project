# 资源加载策略

为了节省服务器资源, 现将一些 通用的 js 加载放到外网去加载.

资源加载列表如下

## js

本地留存 js 位置

```html
   <link rel="stylesheet" href="/static/etc/frame/element-ui/element.css" />
   <link rel="stylesheet" href="/static/etc/frame/font-awesome/css/font-awesome.min.css" />
   <link rel="stylesheet" href="/static/etc/css/base/color.css" />

   <script type="text/javascript" src="/static/etc/frame/jquery/jquery-current.min.js"></script>
   <script type="text/javascript" src="/static/etc/frame/layer/layer.js"></script>
   <script type="text/javascript" src="/static/etc/frame/vue/vue.js"></script>
   <script type="text/javascript" src="/static/etc/frame/element-ui/element.js"></script>
   <script type="text/javascript" src="/static/etc/js/plugins/forge.min.js"></script>
   <script type="text/javascript" src="/static/etc/js/plugins/axios.min.js"></script>
   <script type="text/javascript" src="/static/etc/js/const/dic-base.js"></script>
   <script type="text/javascript" src="/static/etc/js/base/base.js"></script>
   <script type="text/javascript" src="/static/etc/js/base/ity-common-utils.js"></script>
   <script type="text/javascript" src="/static/etc/js/plugins/mock.js"></script>
```

对应网上 js 加载位置

```html

   <link rel="icon" href="/static/etc/img/favicon.ico" type="image/x-icon"/>
   <link rel="stylesheet" href="https://unpkg.com/element-ui@2.15.6/lib/theme-chalk/index.css" />
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha512-SfTiTlX6kk+qitfevl/7LibUOeJWlt9rbyDn92a1DqWOw9vWG2MFoays0sgObmWazO5BQPiFucnnEAjpAB+/Sw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   <link rel="stylesheet" href="/static/etc/css/base/color.css" />

   <script type="text/javascript" src="https://unpkg.com/jquery@3.6.0/dist/jquery.min.js"></script>
   <script type="text/javascript" src="https://unpkg.com/vue@2.6.14/dist/vue.min.js"></script>
   <script type="text/javascript" src="https://unpkg.com/element-ui@2.15.6/lib/index.js"></script>
   <script type="text/javascript" src="https://unpkg.com/md5.js@1.3.5/index.js"></script>
   <script type="text/javascript" src="/static/etc/js/const/dic-base.js"></script>
   <script type="text/javascript" src="/static/etc/js/base/base.js"></script>
   <script type="text/javascript" src="/static/etc/js/base/common-utils.js"></script>

```







