
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
        $.sendPostRequest('singleAgencyHallOpen/isExistPhone', {phone: value}, function (data, status) {
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
        // accountModal.$refs['registerForm'].validate(["pwd1", "pwd2"]);
    }
};

let instNameValidator=(rule, value, callback)=>{
    let exist = accountModal.$data.inst.isExist(value);
    if (exist) {
        accountModal.$data.inst.isExistInst = true;
        callback();
    } else {
        // callback(new Error('not exist'));
        accountModal.$data.inst.isExistInst = false;
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
        let instArrays = this.inst.instArrays;
        $.sendPostRequest('singleAgencyHallOpen/getInstArrInfo', '', data=>{
            instArrays.splice(0, instArrays.length);
            let instList = data.instList;
            instList.forEach(it=>{
                let item = iCodeItem.findItem(dicItyInstitution.status, it.status);
                it.statusText = item.text;
                instArrays.push(it);
            });
        }, true);
    },
    //钩子函数,页面加载完成后执行
    mounted: function () {
    },
    data: {
        activeName : 'login',
        loginFormRules : {
            phone: [
                { required: true, message: '请输入手机号', trigger: 'change' },
                { trigger: 'blur', validator: loginValidPhone }
            ],
            pwd: [
                { required: true, message: '请输入密码', trigger: 'change' },
                { min: 6, max: 20, message: '长度需在6至20位', trigger: 'blur' }
            ],
            captcha: [
                { required: true, message: '请输入图片验证码', trigger: 'change' }
            ]
        },
        loginForm: {
            phone : '',
            pwd : '',
            captcha : ''
        },
        loginDialogVisible: false,
        needLoginCaptcha : false,
        btnGetVerifyCode : {
            text : '获取验证码',
            disabled : false
        },
        inst : {
            instArrays : [],
            isExistInst : false,
            isExist : function (instName) {
                let instArrays = this.instArrays;
                if (!instArrays) {
                   console.log("inst error")
                }
                let find = instArrays.find(it => it.name === instName);
                return Boolean(find);
            }
        },
        registerForm : {
            phone : '',
            pwd1 : '',
            pwd2 : '',
            captcha : '',
            verifyCode : '',
            userName : '',
            instName : '',
            departmentName : '',
            position : '',
            email : ''
        },
        registerFormRules : {
            phone: [
                { required: true, message: '请输入手机号', trigger: 'blur' },
                { trigger: 'blur', validator: registerValidPhone }
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
            ],
            userName: [
                { required: true, message: '请输入用户名', trigger: 'change' },
                { min: 2, max: 20, message: '长度需在2至20位', trigger: 'blur' }
            ],
            instName: [
                { required: true, message: '请输入机构名', trigger: 'change' },
                { min: 2, max: 40, message: '长度需在2至40位', trigger: 'blur' },
                { trigger: 'blur', validator: instNameValidator }
            ],
            departmentName: [
                { required: true, message: '请输入所属部门', trigger: 'change' },
                { min: 2, max: 40, message: '长度需在2至40位', trigger: 'blur' }
            ],
            position: [
                { required: true, message: '请输入职位', trigger: 'change' },
                { min: 2, max: 50, message: '长度需在2至50位', trigger: 'blur' }
            ],
            email: [
                { required: true, message: '请输入邮箱', trigger: 'change' },
                { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' },
                { max: 40, message: '长度最大不能超过40', trigger: 'blur' }
            ]
        },
        registerDialogVisible: false,
        captchaUrl : "yanzhengma.jpg?_=" + Math.random()
    },
    methods: {
        //获取username和password的值，对password值进行MD5加密，然后生成表单提交
        login() {
            this.$refs['loginForm'].validate((valid) => {
                if (valid) {
                    let form = this.loginForm;
                    let pwd = form.pwd;
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
                        username : form.phone,
                        password : passwordMD5,
                        captcha : form.captcha,
                        rememberMe : false
                    };

                    let _this = this;
                    $.sendPostRequest('singleAgencyHallOpen/loginVerification', formParams, function (data) {
                        $.sendFormRequest('validate', formParams, top.document);
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
                    let text = '';
                    if (! this.inst.isExistInst) {
                        text = '已有机构中并未发现和当前机构相符的机构名称, 是否继续?';
                        this.$confirm(text, '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() => {
                            this.doRegister();
                        }).catch(() => {
                        });
                    } else {
                        this.doRegister();
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        doRegister : function(){
            let _this = this;
            $.sendPostRequest('singleAgencyHallOpen/registerAccount', this.registerForm, function (data) {
                const url = 'static/html/singleAgencyQuotationHall/registerProcess.html';
                if (data.inst){
                    window.location = url + '?process=2';
                } else {
                    window.location = url + '?process=3';
                }
                // _this.$alert('审核结果将在2个工作日内发送至您的邮箱, 请注意查收!', '已注册!等待审核', {
                //     confirmButtonText: '确定',
                //     type: 'success',
                //     callback: action => {}
                // });
            },  function (data, status) {
                _this.$message({showClose: true, type: 'warning', message: status.text});
                _this.changeCaptcha();
            });
        },
        toSetPwd() {
            window.location = 'static/html/singleAgencyQuotationHall/resetPassword.html';
        },
        getVerificationCode() {
            let form = this.registerForm;
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
            $.sendPostRequest("singleAgencyHallOpen/sendOpenHallRegisterSmsCode", params, function (data) {
                _this.$message({showClose: true, type: 'warning', message: '验证码发送成功!'});
                disableValForAWhile(_this.btnGetVerifyCode, '重新发送');
            }, function (data, status) {
                _this.$message({showClose: true, type: 'warning', message: status.text});
                _this.changeCaptcha();
            });
        },
        changeCaptcha () {
            this.captchaUrl = "yanzhengma.jpg?_=" + Math.random();
        },
        querySearchAsync(queryString, cb) {
            console.log('querySearchAsync');
            let instArrays = this.inst.instArrays;
            let results = queryString ? instArrays.filter(it=>it.name.indexOf(queryString) > -1) : instArrays;
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => { cb(results); }, 100);
        },
        handleSelect(item) {
            console.log('handleSelect');
            this.registerForm.instName = item.name;
            this.registerForm.instGuid = item.uuid;
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
