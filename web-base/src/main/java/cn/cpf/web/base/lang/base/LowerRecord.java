package cn.cpf.web.base.lang.base;

import java.util.HashMap;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/3 20:03
 **/
public class LowerRecord extends HashMap<String, Object> implements IGetter {

    @Override
    public Object put(String key, Object value) {
        if (key == null || "".equals(key.trim())) {
            return "";
        }
        return super.put(key.toLowerCase(), value);

    }

    @Override
    public Object get(String key) {
        return super.get(key);
    }

    public void set(String key, String value) {
        super.put(key, value);
    }

}
