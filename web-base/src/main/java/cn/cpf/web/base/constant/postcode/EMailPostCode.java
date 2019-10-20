package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b> 关于邮箱发送验证服务的枚举
 *
 * @author CPF
 * @date 2019/3/12 10:57
 **/
public enum EMailPostCode implements IPostCode {

    DEFAULT("0000", "未发现异常!"),
    FREQUENTLY_SEND("7009", "您发送频率太过频繁! 请稍后再试!"),
    EMAIL_CONFLICT("7001", "该邮箱已被其他人绑定!"),
    EMAIL_REPEAT_BINDING("7002", "你已成功绑定该邮箱, 请确认!"),
    EMAIL_UPDATE_FAILURE("7004", "邮箱地址更新失败"),
    VERIFICATION_NOT_FOUND("7005", "未查到验证信息"),
    VERIFICATION_NOT_MATCH("7006", "验证失败"),
    VERIFICATION_EXPIRED("7007", "邮箱验证过期"),
    VERIFICATION_FAILURE("7008", "验证失败"),
    EMAIL_REGEX_ERROR("7009", "邮箱格式错误!"),;

    private String code;

    private String text;

    private String desc;

    EMailPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EMailPostCode(String code, String text) {
        this(code, text, text);
    }

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

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }
}
