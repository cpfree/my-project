package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/2 16:39
 **/
public enum EGroupPostCode implements IPostCode {

    groupHasNoThisUser("1235", "小市场中没有发现此用户"),
    groupIsNotFound("1233", "未发现小市场数据"),
    groupDisabled("1234", "小市场被禁用"),
    groupNameChangeFailure("1230", "小市场信息修改失败"),
    groupNameIsDuplicate("1231", "小市场名称重复"),
    groupNameIsEqualsOld("1232", "小市场名称并没有被编辑, 请确认是否修改了小市场信息"),
    groupNameChangeSuccess("0210", "小市场名称修改成功");

    private String code;

    private String text;

    private String desc;

    EGroupPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EGroupPostCode(String code, String text) {
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

