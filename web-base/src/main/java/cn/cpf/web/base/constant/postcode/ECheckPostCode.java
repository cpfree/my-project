package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/6/17 19:13
 **/
@SuppressWarnings({"AlibabaEnumConstantsMustHaveComment", "java:S115"})
public enum ECheckPostCode implements IPostCode {

    instNameIsExist("0501", "机构名称已经存在!");

    private final String code;

    private final String text;

    private final String desc;

    ECheckPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    ECheckPostCode(String code, String text) {
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
