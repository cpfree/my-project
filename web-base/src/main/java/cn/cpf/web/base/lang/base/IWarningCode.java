package cn.cpf.web.base.lang.base;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/25 11:42
 **/
public interface IWarningCode {

    /**
     * 获取代码
     *
     * @return 代码
     */
    String getCode();

    /**
     * 获取展示信息
     *
     * @return 展示信息
     */
    String getText();

    /**
     * 获取解释信息
     *
     * @return 解释
     */
    String getDesc();

    /**
     * 返回postCode的Json字符串信息
     */
    default String toJsonString() {
        return String.format("{\"code\" : \"%s\", \"text\" : \"%s\", \"desc\" : \"%s\"}", getCode(), getText(), getDesc());
    }

}
