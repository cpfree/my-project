package cn.cpf.web.base.lang.base;

import java.util.HashMap;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/23 11:16
 **/
public class CamelRecord extends HashMap<String, Object> implements IGetter {

    /**
     * 将Key转换为驼峰式
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Object put(String key, Object value) {
        if (key == null || "".equals(key.trim())) {
            return "";
        }
        if (key.indexOf('_') > -1) {
            int len = key.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                char c = key.charAt(i);
                if (c == '_') {
                    if (++i < len) {
                        sb.append(Character.toUpperCase(key.charAt(i)));
                    }
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
            key = sb.toString();
        }

        return super.put(key, value);
    }

    @Override
    public Object get(String key) {
        return super.get(key);
    }

    /**
     * 将Key转换为驼峰式
     *
     * @param key
     * @param value
     * @return
     */
    public Object set(String key, Object value) {
        return super.put(key, value);
    }

}
