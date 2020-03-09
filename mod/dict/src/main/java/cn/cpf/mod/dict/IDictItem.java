package cn.cpf.mod.dict;

import org.apache.commons.lang3.StringUtils;

public interface IDictItem {

    /**
     * 通过value来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param value 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配value的枚举对象
     */
    static <T extends IDictItem> T getByCode(Class<T> enumClass, String value) {
        if (enumClass == null) {
            return null;
        }
        //通过反射取出Enum所有常量的属性值
        for (T each : enumClass.getEnumConstants()) {
            //利用value进行循环比较，获取对应的枚举
            if (value.equals(each.value())) {
                return each;
            }
        }
        return null;
    }

    /**
     * 通过label来获取enumClass中匹配的枚举对象
     *
     * @param enumClass 枚举类
     * @param label 代码
     * @param <T> 模板类型
     * @return 如果 enumClass为空, 返回 null, 否则返回枚举类中第一个匹配label的枚举对象
     */
    static <T extends IDictItem> T getByText(Class<T> enumClass, String label) {
        if (enumClass == null) {
            return null;
        }
        //通过反射取出Enum所有常量的属性值
        for (T each : enumClass.getEnumConstants()) {
            //利用value进行循环比较，获取对应的枚举
            if (label.trim().equals(each.label())) {
                return each;
            }
        }
        return null;
    }

    /**
     * 通过 value 来获取 label
     *
     * @param enumClass 枚举类
     * @param value 枚举代码
     * @param <T> 模板类型
     * @return 如果 value为空
     */
    static <T extends IDictItem> String getTextByCode(Class<T> enumClass, String value) {
        if (value == null) {
            return "";
        }
        IDictItem byCode = getByCode(enumClass, value);
        if (null == byCode) {
            return value;
        }
        return byCode.label();
    }

    /**
     * 通过 value 来获取 label
     *
     * @param enumClass 枚举类
     * @param label 枚举代码
     * @param <T> 模板类型
     * @return 如果 value为空
     */
    static <T extends IDictItem> String getValueByLable(Class<T> enumClass, String label) {
        if (label == null) {
            return "";
        }
        label = label.trim();
        IDictItem byCode = getByText(enumClass, label);
        if (null == byCode) {
            return label;
        }
        return byCode.value();
    }

    String name();

    default String value() {
        return getItemBean().getValue();
    }

    default String label() {
        return getItemBean().getLabel();
    }

    default DictItemBean getItemBean(){
        return StaticDictPool.getDictItem(this);
    }

    default boolean isCode(String value) {
        return StringUtils.isNotBlank(value) && value.endsWith(value());
    }

}
