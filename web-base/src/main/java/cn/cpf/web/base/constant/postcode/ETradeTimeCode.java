package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/3/20 15:25
 **/
public enum ETradeTimeCode implements IPostCode {

    editTimeNotInSysTradeTime("3001", "编辑时间不在系统交易时间内"),
    editTimeNotInInstitutionTradeTime("3002", "编辑时间不在机构交易时间内"),
    editTimeNotBeforeCloseTime("3003", "编辑时间开始时间不在交易事件之前"),
    notFoundSysTradeTime("3004", "系统交易时间为空, 请检查系统交易时间配置", ""),
    editTimeEqOldTime("3005", "交易时间并没有修改"),
    sysEditTimeEqOldTime("3006", "系统交易时间并没有修改"),

    openTimeIsChanged("0307", "开始交易时间修改成功"),
    closeTimeIsChanged("0308", "闭市交易时间修改成功"),
    doubleTimeIsChanged("0309", "开闭市交易时间修改成功");

    private String code;

    private String text;

    private String desc;

    ETradeTimeCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    ETradeTimeCode(String code, String text) {
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
