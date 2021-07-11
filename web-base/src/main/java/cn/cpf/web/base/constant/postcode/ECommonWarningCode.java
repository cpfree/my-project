package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IWarningCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/25 11:31
 **/
public enum ECommonWarningCode implements IWarningCode {

    /**
     * 应该查询出一条,却查询出了多条数据
     */
    atMostOneButFoundMore("0909", "查询异常", "最多应该查询出一个但是发现多个数据");

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

    ECommonWarningCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    ECommonWarningCode(String code, String text) {
        this(code, text, text);
    }
}