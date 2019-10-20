package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

public enum ELoginPostCode implements IPostCode {

    DEFAULT("0000", "未发现异常"),
    LOGIN_SUCCESS("0123", "登陆成功"),
    LOGIN_FAILURE("1401", "账户密码验证失败"),
    PHONE_PWD_MATCH("1402", "手机号和密码匹配成功"),
    VERIFICATION_CODE_NOT_FOUND("1403", "请输入图片验证码"),
    VERIFICATION_CODE_ERROR("1404", "图片验证码错误"),
    VERIFICATION_CODE_INVALIDATION("1405", "图片验证码失效"),
    USER_OR_PASSWORD_ERROR("1406", "用户名或密码错误"),
    USER_NOT_FOUND("1407", "未发现用户"),
    ACCOUNT_IS_EXIST("1408", "账号已存在, 请直接登陆或修改密码"),
    ACCOUNT_IS_DISABLED("1409", "您的账号已被禁用，请联系您的机构管理员"),
    ACCOUNT_IS_CLOSED("1410", "用户已注销"),
    ACCOUNT_IS_PREPARATION("1411", "用户待激活"),
    USER_ROLE_NOT_FOUND("1412", "未发现用户角色"),
    USER_INSTITUTION_IS_NOT_FOUND("1413", "未发现用户所属机构"),
    USER_INSTITUTION_IS_NOT_UNIQUE("1414", "用户机构不唯一"),
    INSTITUTIONAL_ABNORMAL_STATE("1415", "机构非正常状态"),
    doublePwdIsNotEquals("1416", "两个密码不一致"),

    // 开发大厅
    youAccountIsWaitingInstManagerVerify("1417", "当前手机号已注册, 现在在审核中, 请等待机构管理员审核!");

    private String code;

    private String text;

    private String desc;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    ELoginPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    ELoginPostCode(String code, String text) {
        this(code, text, text);
    }

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }
}