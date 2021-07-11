package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/23 16:00
 **/
public enum EFormPostCode implements IPostCode {

    insertSuccess("0101", "新增成功"),
    insertFailure("5101", "新增失败"),
    deleteSuccess("0101", "删除成功"),
    deleteFailure("5102", "删除失败"),
    updateSuccess("0101", "修改成功"),
    updateFailure("5103", "修改失败"),
    ;

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

    EFormPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EFormPostCode(String code, String text) {
        this(code, text, text);
    }

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }


}
