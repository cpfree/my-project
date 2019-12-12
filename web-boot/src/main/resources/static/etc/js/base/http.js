"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (_) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
exports.__esModule = true;
var axios_1 = require("axios");
function isShowLoading(bool) {
    console.log('loading : ' + bool);
}
// 创建实例时设置配置的默认值
/**
 * 自定义实例默认值
 */
var instance = axios_1["default"].create({
    // baseURL: 'http://yapi.demo.qunar.com/mock/1152', // 公	共接口url（如果有多个的公共接口的话，需要处理）
    timeout: 3000
});
// /api/getUserById
// 请求拦截器, 进行一个全局loading  加载，这种情况下所有的接口请求前 都会加载一个loading
/**
 * 添加请求拦截器 ，意思就是发起请求接口之前做什么事，一般都会发起加载一个loading
 * */
//  如果不想每个接口都加载loading ，就注释掉请求前拦截器,在http这个类中处理
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么（... 这里写你的展示loading的逻辑代码 ）
    isShowLoading(true);
    // 获取token，配置请求头
    // const TOKEN = localStorage.getItem('Token')
    // 演示的token（注意配置请求头，需要后端做cros跨域处理，我这里自己前端配的跨域）
    var TOKEN = '1fd399bdd9774831baf555ae5979c66b';
    if (TOKEN) {
        // 配置请求头 token
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded';
        config.headers['Authorization'] = TOKEN;
    }
    return config;
}, function (error) {
    // 对请求错误做些什么，处理这个错误
    // 可以直接处理或者展示出去,toast show()
    console.warn(error);
    return Promise.reject(error);
});
/**
 * 添加响应拦截器，意思就是发起接口请求之后做什么事，此时只有两种情况，
 * 要么成功，要么失败，但是不管成功，还是失败，我们都需要关闭请求之前的
 * 发起的loading，那既然要处理loading，就把loading做成全局的了，
 * 这里自定义一个处理加载loding 和关闭loading的方法，而且这个loading
 * 要不要加载，会根据外部传入的布尔值来决定，默认是false:不展示
 * */
instance.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    isShowLoading(false);
    console.log(response);
    // 根据你们家的后端定义请求过期后返回的参数，处理token过期问题
    // 我这个接口木有token啊，这里演示下
    // 判断
    var status = response.data.status;
    // 判断状态码401或者其它条件，不知道判断哪个的去问你家后台
    if (Object.is(status, 401)) {
        // token过期后处理
        // 1.删除你本地存储的那个过期的token
        // 2. 跳转到登陆页（因为没有装路由，不写了，重新登陆赋值）
        //  todo...
    }
    return response;
}, function (error) {
    // 对响应错误做点什么
    isShowLoading(false);
    return Promise.reject(error);
});
/**
 * 使用es6中的类，进行简单封装
 */
var http = /** @class */ (function () {
    function http() {
    }
    // 使用async ... await
    http.get = function (url, params) {
        return __awaiter(this, void 0, void 0, function () {
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        console.log(params);
                        return [4 /*yield*/, instance.get(url, { params: params })];
                    case 1: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    http.post = function (url, params) {
        return __awaiter(this, void 0, void 0, function () {
            return __generator(this, function (_a) {
                switch (_a.label) {
                    case 0:
                        console.log(params);
                        return [4 /*yield*/, instance.post(url, params)];
                    case 1: return [2 /*return*/, _a.sent()];
                }
            });
        });
    };
    return http;
}());
exports["default"] = http;
