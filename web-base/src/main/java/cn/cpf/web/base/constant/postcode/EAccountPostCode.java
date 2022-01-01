package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/3/27 19:01
 **/
@SuppressWarnings({"AlibabaEnumConstantsMustHaveComment", "java:S115"})
public enum EAccountPostCode implements IPostCode {

    /**
     * 未发现有效账户
     */
    notFoundActiveAccount("3011", "未发现有效账户, 请检查相关信息是否正确"),
    registerFailure("3012", "注册失败"),
    registerSuccess("0012", "注册成功");

    private final String code;

    private final String text;

    private final String desc;

    EAccountPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EAccountPostCode(String code, String text) {
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
