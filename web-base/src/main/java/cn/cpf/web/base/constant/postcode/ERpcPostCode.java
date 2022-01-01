package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/10 11:19
 **/
@SuppressWarnings({"AlibabaEnumConstantsMustHaveComment", "java:S115"})
public enum ERpcPostCode implements IPostCode {

    rpcKeyIdErr("5678", "远程调用标记错误"),
    rpcInnerErr("5679", "远程调用服务器内部发生错误");

    private final String code;

    private final String text;

    private final String desc;

    ERpcPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    ERpcPostCode(String code, String text) {
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