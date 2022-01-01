package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b> SMS返回结果代码
 *
 * @author CPF
 * @date 2019/3/18 20:15
 **/
@SuppressWarnings({"AlibabaEnumConstantsMustHaveComment", "java:S115"})
public enum ESmsPostCode implements IPostCode {

    SUCCESS("0000", "Success"),
    VERIFICATION_SUCCESS("0000", "验证成功"),
    ILLEGAL_ARGUMENT_EXCEPTION("5980", "参数检查异常!"),
    ALI_YUN_ERROR_RECEIPT("5201", "SMS短信服务调用发生错误"),
    SEND_FAILURE("5100", "短信发送失败"),
    CLIENT_EXCEPTION("5501", "短信服务异常", "客户端(相对于阿里云SMS)连接异常, 请检查服务器是否能够正常解析和访问sms.aliyuncs.com"),
    SMS_IS_CLOSE("5502", "sms服务已关闭"),
    VERIFICATION_FAILURE("5401", "短信验证码校验失败"),
    PHONE_NUMBER_IS_BLANK("5402", "手机号码为空"),
    TEMPLATE_CODE_IS_BLANK("5403", "手机号码为空", "未发现短信模板编号"),
    VERIFICATION_CODE_EXPIRED("5404", "短信验证码错误或已过期");

    private final String code;

    private final String text;

    private final String desc;

    ESmsPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    ESmsPostCode(String code, String text) {
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
