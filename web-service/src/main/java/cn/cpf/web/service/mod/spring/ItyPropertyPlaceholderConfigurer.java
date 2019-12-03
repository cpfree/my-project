package cn.cpf.web.service.mod.spring;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/11 11:32
 **/
public class ItyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final Map<String, String> propertyMap = new HashMap<>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    //static method for accessing context properties
    public static String getProperty(String name) {
        return propertyMap.get(name);
    }

    //static method for accessing context properties
    public static String getProperty(String name, String defaultVal) {
        final String s = propertyMap.get(name);
        if (s == null || s.trim().isEmpty()) {
            return defaultVal;
        }
        return s;
    }

    public static Integer getInteger(String name) {
        return getInteger(name, null);
    }

    public static Integer getInteger(String name, Integer defaultVal) {
        final String s = propertyMap.get(name);
        if (s == null || s.trim().isEmpty()) {
            return defaultVal;
        }
        return Integer.valueOf(s);
    }

    public static boolean getBoolean(String name) {
        return getBoolean(name, null);
    }

    public static Boolean getBoolean(String name, Boolean defaultVal) {
        final String s = propertyMap.get(name);
        if (s == null || s.trim().isEmpty()) {
            return defaultVal;
        }
        return Boolean.valueOf(s);
    }


}