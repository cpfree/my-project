package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/6/14 15:08
 **/
@SuppressWarnings({"AlibabaEnumConstantsMustHaveComment", "java:S115"})
public enum EExceptionPostCode implements IPostCode {

    notFoundInstGuidFromSession("5901", "未发现您的机构或会话过期", "未从session中发现您的机构信息");

    private final String code;

    private final String text;

    private final String desc;

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

    EExceptionPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EExceptionPostCode(String code, String text) {
        this(code, text, text);
    }

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }
}