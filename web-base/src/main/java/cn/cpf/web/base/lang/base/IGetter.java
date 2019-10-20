package cn.cpf.web.base.lang.base;

import java.math.BigDecimal;
import java.util.Date;

public interface IGetter {

    Object get(String key);

    default <T> T get(String key, Class<T> returnClass) {
        return returnClass.cast(get(key));
    }

    default String getString(String key) {
        return (String) get(key);
    }

    default BigDecimal getBigDecimal(String key) {
        return (BigDecimal) get(key);
    }

    default Date getDate(String key) {
        return (Date) get(key);
    }

    default Integer getInteger(String key) {
        return (Integer) get(key);
    }

    default Object getDefault(String key, Object defaultObj) {
        Object o = get(key);
        if (o == null) {
            return defaultObj;
        }
        return o;
    }

}
