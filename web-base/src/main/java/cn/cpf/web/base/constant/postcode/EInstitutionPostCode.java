package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/3/27 19:01
 **/
public enum EInstitutionPostCode implements IPostCode {

    institutionNotFound("1101", "未发现此机构"),
    institutionStateNormal("0101", "机构状态正常"),
    institutionStateAbnormal("1101", "未发现此机构"),
    institutionHasNoManager("0132", "机构没有管理员", "绑定机构时, 机构没有管理员, 而绑定的人又是游客身份, 没有管理员去审核!"),
    updateCounterpartError("9023", "更新交易对手准入业务信息失败");

    private String code;

    private String text;

    private String desc;

    EInstitutionPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EInstitutionPostCode(String code, String text) {
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
