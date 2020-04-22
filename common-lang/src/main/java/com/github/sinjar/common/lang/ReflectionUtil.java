package com.github.sinjar.common.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建一个实例
     */
    public static Object newInstance(Class<?> cls) {
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("new Instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 映射调用方法
     * @param obj 对象
     * @param method 方法
     * @param params 参数
     * @return 调用映射方法的结果
     */
    public static Object invokeMethod(Object obj, Method method, Object... params) {
        Object result = null;
        method.setAccessible(true);
        try {
            result = method.invoke(obj, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("invokeMethod failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     *
     * @param obj 对象
     * @param field 成员变量
     * @param val 值
     */
    public static void setField(Object obj, Field field, Object val) {
        field.setAccessible(true);
        try {
            field.set(obj,val);
        } catch (IllegalAccessException e) {
            LOGGER.error("invoke set setField failure", e);
            throw new RuntimeException(e);
        }
    }

}
