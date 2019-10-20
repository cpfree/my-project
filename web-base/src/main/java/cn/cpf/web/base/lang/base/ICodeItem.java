package cn.cpf.web.base.lang.base;

public interface ICodeItem {

    /**
     * 通过code来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param code 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配code的枚举对象
     */
    static <T extends ICodeItem> T getByCode(Class<T> enumClass, String code) {
        if (enumClass == null) {
            return null;
        }
        //通过反射取出Enum所有常量的属性值
        for (T each : enumClass.getEnumConstants()) {
            //利用code进行循环比较，获取对应的枚举
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    /**
     * 通过text来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param text 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配text的枚举对象
     */
    static <T extends ICodeItem> T getByText(Class<T> enumClass, String text) {
        if (enumClass == null) {
            return null;
        }
        //通过反射取出Enum所有常量的属性值
        for (T each : enumClass.getEnumConstants()) {
            //利用code进行循环比较，获取对应的枚举
            if (text.trim().equals(each.getText())) {
                return each;
            }
        }
        return null;
    }

    /**
     * 通过 code 来获取 text
     *
     * @param enumClass 枚举类
     * @param code 枚举代码
     * @param <T> 模板类型
     * @return 如果 code为空
     */
    static <T extends ICodeItem> String getTextByCode(Class<T> enumClass, String code) {
        if (code == null) {
            return "";
        }
        ICodeItem byCode = getByCode(enumClass, code);
        if (null == byCode) {
            return code;
        }
        return byCode.getText();
    }

    /**
     * 通过 code 来获取 text
     *
     * @param enumClass 枚举类
     * @param text 枚举代码
     * @param <T> 模板类型
     * @return 如果 code为空
     */
    static <T extends ICodeItem> String getCodeByText(Class<T> enumClass, String text) {
        if (text == null) {
            return "";
        }
        text = text.trim();
        ICodeItem byCode = getByText(enumClass, text);
        if (null == byCode) {
            return text;
        }
        return byCode.getCode();
    }

    default String getCode() {
        return CodeItemPool.getCodeItem(this).code;
    }

    default String getText() {
        return CodeItemPool.getCodeItem(this).text;
    }

    default boolean isCode(String code) {
        return CodeItemPool.getCodeItem(this).code.equals(code);
    }

    default boolean isNotEquals(ICodeItem codeItem) {
        return this != codeItem;
    }

    default CodeItemBean of(String code) {
        ICodeItem[] enumConstants = this.getClass().getEnumConstants();
        for (ICodeItem item : enumConstants) {
            if (item.getCode().equals(code)) {
                return CodeItemPool.getCodeItem(this);
            }
        }
        // 空对象模式
        return new CodeItemBean("", "");
    }

}
