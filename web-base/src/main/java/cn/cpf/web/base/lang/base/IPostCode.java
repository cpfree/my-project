package cn.cpf.web.base.lang.base;

/**
 * <b>Description : </b> 往前台传递调用信息的接口
 *
 * @author CPF
 * @date 2019/3/12 10:34
 **/
public interface IPostCode {

    /**
     * 获取代码
     * @return 代码
     */
    String getCode();

    /**
     * 获取展示信息
     * @return 展示信息
     */
    String getText();

    /**
     * 获取解释信息
     * @return 解释
     */
    String getDesc();

    /**
     *
     * @param code postCode 的代码
     * @return postCode 的代码代表是否是成功
     */
    static boolean isSuccess(String code) {
        return code != null && code.startsWith("0");
    }

    default boolean isSuccess() {
        return isSuccess(this.getCode());
    }

    default boolean isNotSuccess() {
        return !isSuccess();
    }

    /**
     * 返回postCode的Json字符串信息
     */
    default String toJsonString() {
        return String.format("{\"code\" : \"%s\", \"text\" : \"%s\", \"desc\" : \"%s\"}", getCode(), getText(), getDesc());
    }

}
