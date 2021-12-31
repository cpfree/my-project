
//     $('#app').click(function() {
//       $.ajax({
//           url: 'http://api.yourdomain.com/getlist/',
//           type:'get',
//           dataType :"json"
//       }).done(function (data,status,xhr) {
//           console.log(data,null,2)
//       })
//   })
//   //定义mock数据
//   var obj ={ aa : '11',bb:'22',cc:'33',dd:'44'}
//   //对数据进行拦截
//   Mock.mock('http://api/yourdomain.com/getlist/',{
//       //定义模板语法
//       "user|1-3":[
//           {
//               "id|+1":1,
//              name:"@cname",
//               "age|18-28":0,
//               birthday:'@data("yyyy-MM-dd")',
//               city:"@city",
//               "fromobj|2":obj
//           }
//       ]
//   })

let apiUrl = {
   loginPage: '/login',
   resetPasswordPage: 'static/page/account/resetPassword.html',
   request: {
      isExistPhone: '/noAccount/isExistPhone',
      loginVerification: '/login',
      registerAccount: '/sign/up'
   }
}

let loginValidPhone=(rule, value,callback)=>{
    if (!value){
        callback(new Error('请输入电话号码'))
    }else  if (!isValidPhone(value)){
        callback(new Error('请输入正确的11位手机号码'))
    }else {
        callback();
    }
};

let registerValidPhone=(rule, value,callback)=>{
    if (!value){
        callback(new Error('请输入电话号码'))
    }else  if (!isValidPhone(value)){
        callback(new Error('请输入正确的11位手机号码'))
    }else {
        $.sendPostRequest(apiUrl.request.isExistPhone, {phone: value}, function (data, status) {
            callback();
        }, function (data, status) {
            callback(new Error(status.desc));
        });
    }
};

let validConfirmPwd=(rule, value, callback)=>{
    let pwd1 = accountModal.$data.registerForm.pwd1;
    let pwd2 = accountModal.$data.registerForm.pwd2;
    if (pwd1 && pwd2 && pwd1 !== pwd2) {
        callback(new Error('两次密码输入不一致'));
    } else {
        callback();
    }
};

function isValidPhone(str) {
    const reg = /^1[3|4|5|7|8|9][0-9]\d{8}$/;
    return reg.test(str)
}

let accountModal = new Vue({
    el: '#accountModal',
    //钩子函数,页面加载执行
    created: function () {
    },
    //钩子函数,页面加载完成后执行
    mounted: function () {
    },
    data: {
        activeName : 'login',
        loginFormRules : {
            username: [
                { required: true, message: '请输入账户名', trigger: 'change' },
            ],
            password: [
                { required: true, message: '请输入密码', trigger: 'change' },
                { min: 6, max: 20, message: '长度需在6至20位', trigger: 'blur' }
            ],
            captcha: [
                { required: true, message: '请输入图片验证码', trigger: 'change' }
            ]
        },
        loginForm: {
            username : '',
            password : '',
            captcha : ''
        },
        loginDialogVisible: false,
        needLoginCaptcha : false,
        btnGetVerifyCode : {
            text : '获取验证码',
            disabled : false
        },
        registerForm : {
            username : '',
            pwd1 : '',
            pwd2 : '',
            captcha : '',
            email : ''
        },
        registerFormRules : {
            username: [
               { required: true, message: '请输入账户名', trigger: 'change' },
               { min: 2, max: 20, message: '长度需在2至20位', trigger: 'blur' }
            ],
            pwd1: [
                { required: true, message: '请输入密码', trigger: 'change' },
                { min: 6, max: 20, message: '长度需在6至20位', trigger: 'blur' },
                { trigger: 'blur', validator: validConfirmPwd }
            ],
            pwd2: [
                { required: true, message: '请输入确认密码', trigger: 'change' },
                { min: 6, max: 20, message: '长度需在6至20位', trigger: 'blur' },
                { trigger: 'blur', validator: validConfirmPwd }
            ],
            captcha: [
                { required: true, message: '请输入图片验证码', trigger: 'change' }
            ],
            email: [
                { required: true, message: '请输入邮箱', trigger: 'change' },
                { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' },
                { max: 40, message: '长度最大不能超过40', trigger: 'blur' }
            ]
        },
        registerDialogVisible: false,
        captchaUrl : "kaptcha?_=" + Math.random()
    },
    methods: {
        //获取username和password的值，对password值进行MD5加密，然后生成表单提交
        login() {
            this.$refs['loginForm'].validate((valid) => {
                if (valid) {
                    let form = this.loginForm;
                    let password = form.password;
                    let md = forge.md.md5.create();
                    md.update(password);
                    let passwordMD5 = md.digest().toHex();
                    let nullMd = forge.md.md5.create();
                    nullMd = nullMd.update("");
                    let nullPassword = nullMd.digest().toHex();
                    if (passwordMD5 === nullPassword) {
                        layer.msg("密码为空");
                        return ;
                    }
                    let formParams = {
                        username : form.username,
                        password : passwordMD5,
                        captcha : form.captcha,
                        rememberMe : false
                    };

                    let _this = this;
                    $.sendPostRequest(apiUrl.request.loginVerification, formParams, function (data) {
                        console.log('登录成功');
                    },  function (data, status) {
                        if (data.needCaptcha) {
                            _this.needLoginCaptcha = data.needCaptcha;
                        }
                        _this.$message({showClose: true, type: 'warning', message: status.text});
                        _this.changeCaptcha();
                    });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //获取username和password的值，对password值进行MD5加密，然后生成表单提交
        register() {
            this.$refs['registerForm'].validate((valid) => {
                if (valid) {
                    this.doRegister();
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        doRegister : function(){
            let _this = this;
            let form = this.registerForm;
            let password = form.pwd1;
            let md = forge.md.md5.create();
            md.update(password);
            let passwordMD5 = md.digest().toHex();
            let nullMd = forge.md.md5.create();
            nullMd = nullMd.update("");
            let nullPassword = nullMd.digest().toHex();
            if (passwordMD5 === nullPassword) {
                layer.msg("密码为空");
                return ;
            }
            let formParams = {
                username : form.username,
                password : passwordMD5,
                captcha : form.captcha,
                email : form.email
            };
            $.sendPostRequest(apiUrl.request.registerAccount, formParams, function (data) {
                _this.$alert('注册成功', {
                    confirmButtonText: '确定',
                    type: 'success',
                    callback: action => {
                       window.location = apiUrl.loginPage + '?process=2';
                    }
                });
            },  function (data, status) {
                _this.$message({showClose: true, type: 'warning', message: status.text});
                _this.changeCaptcha();
            });
        },
        toSetPwd() {
            window.location = apiUrl.resetPasswordPage;
        },
        changeCaptcha () {
            this.captchaUrl = "kaptcha?_=" + Math.random();
        }
    },
    computed: {},
    watch: {
        'table.conditions.busType': {
            handler(newValue) {
            },
            immediate: false
        },
        activeName : function () {
            this.changeCaptcha();
        }
    }
});



// 倒计时
function disableValForAWhile(btn, initText, str, time) {
    // 备份
    initText = initText || '';
    time = time || 60;
    str = str || '重新发送({1})';
    // clear function
    btn.text = String.format(str, time--);
    btn.disabled = true;
    let clear = function (id) {
        clearInterval(id);
        btn.text = initText;
        btn.disabled = false;
    };
    let innerId = setInterval(() => {
        btn.text = String.format(str, time--);
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
