
let validPhone=(rule, value,callback)=>{
    if (!value){
        callback(new Error('请输入电话号码'))
    }else  if (!isValidPhone(value)){
        callback(new Error('请输入正确的11位手机号码'))
    }else {
        callback()
    }
};

let validConfirmPwd=(rule, value, callback)=>{
    let pwd1 = accountModal.$data.resetPasswordForm.pwd1;
    let pwd2 = accountModal.$data.resetPasswordForm.pwd2;
    if (pwd1 && pwd2 && pwd1 !== pwd2) {
        callback(new Error('两次密码输入不一致'));
    } else {
        callback();
        // accountModal.$refs['resetPasswordForm'].validate(["pwd1", "pwd2"]);
    }
};

function isValidPhone(str) {
    const reg = /^1[3|4|5|7|8|9][0-9]\d{8}$/;
    return reg.test(str)
}

let accountModal = new Vue({
    el: '#resetPasswordModal',
    //钩子函数,页面加载执行
    created: function () {
    },
    //钩子函数,页面加载完成后执行
    mounted: function () {
    },
    data: {
        activeName : 'resetPassword',
        btnGetVerifyCode : {
            text : '获取验证码',
            disabled : false
        },
        resetPasswordForm : {
            phone : '',
            pwd1 : '',
            pwd2 : '',
            captcha : '',
            verifyCode : ''
        },
        resetPasswordFormRules : {
            phone: [
                { required: true, message: '请输入手机号', trigger: 'blur' },
                { trigger: 'blur', validator: validPhone }
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
            verifyCode: [
                { required: true, message: '请输入短信验证码', trigger: 'change' }
            ]
        },
        captchaUrl : "kaptcha?_=" + Math.random()
    },
    methods: {
        //获取username和password的值，对password值进行MD5加密，然后生成表单提交
        resetPassword() {
            this.$refs['resetPasswordForm'].validate((valid) => {
                if (valid) {
                    this.doResetPassword();
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        doResetPassword : function(){
            let _this = this;

            let form = this.resetPasswordForm;
            let pwd = form.pwd1;
            let md = forge.md.md5.create();
            md.update(pwd);
            let passwordMD5 = md.digest().toHex();
            let nullMd = forge.md.md5.create();
            nullMd = nullMd.update("");
            let nullPassword = nullMd.digest().toHex();
            if (passwordMD5 === nullPassword) {
                layer.msg("密码为空");
                return ;
            }
            let formParams = {
                phone : form.phone,
                pwd1 : passwordMD5,
                pwd2 : passwordMD5,
                captcha : form.captcha,
                verifyCode : form.verifyCode
            };

            $.sendPostRequest('singleAgencyHallOpen/resetPassword', formParams, function (data) {
                _this.$alert('密码重置成功!', '请登陆账号', {
                    confirmButtonText: '跳转至登陆页面',
                    type: 'success',
                    callback: action => {
                        window.location='static/html/singleAgencyQuotationHall/accountModal.html';
                    }
                });
            },  function (data, status) {
                _this.$message({showClose: true, type: 'warning', message: status.text});
                _this.changeCaptcha();
            });
        },
        getVerificationCode() {
            let form = this.resetPasswordForm;
            if (!form.phone) {
                this.$message({
                    showClose: true,
                    message: '请先填写手机号码',
                    type: 'warning'
                });
                return;
            }
            if (!form.captcha) {
                this.$message({
                    showClose: true,
                    message: '请先填写图片验证码',
                    type: 'warning'
                });
                return;
            }
            let _this = this;
            let params = {phone : form.phone, captcha: form.captcha};
            $.sendPostRequest("singleAgencyHallOpen/sendResetPwdSmsCode", params, function (data) {
                _this.$message({showClose: true, type: 'warning', message: '验证码发送成功!'});
                disableValForAWhile(_this.btnGetVerifyCode, '重新发送');
            }, function (data, status) {
                _this.$message({showClose: true, type: 'warning', message: status.text});
                _this.changeCaptcha();
            });
        },
        changeCaptcha () {
            this.captchaUrl = "kaptcha?_=" + Math.random();
        }
    },
    computed: {},
    watch: {
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
