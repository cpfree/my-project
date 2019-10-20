package cn.cpf.web.base.util.validate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class RequireUtil {

    /**
     * 为空则抛出异常
     *
     * @param str
     */
    public static void requireStringNonBlank(String str) {
        if (str == null || str.length() == 0) {
            ExceptionUtil.throwRuntimeException("字符串为空!");
        }
    }

    /**
     * 为空则抛出异常
     *
     * @param strs
     */
    public static void requireStringNonBlank(String... strs) {
        for (String str : strs) {
            requireStringNonBlank(str);
        }
    }

    /**
     * 为空则抛出异常
     */
    public static <T> void requireCollectNonBlank(Collection<T> collection) {
        Objects.requireNonNull(collection);
        if (collection.isEmpty()) {
            ExceptionUtil.throwRuntimeException("集合为空!");
        }
    }

    /**
     * 为空则抛出异常
     */
    public static void requireMapNonBlank(Map<?, ?> map) {
        Objects.requireNonNull(map);
        if (map.isEmpty()) {
            ExceptionUtil.throwRuntimeException("map为空!");
        }
    }

    /**
     * flag不为true则抛出异常
     *
     * @param flag
     */
    public static void requireBooleanTrue(boolean flag) {
        if (!flag) {
            ExceptionUtil.throwRuntimeException();
        }
    }

    /**
     * arrs 中不包含 target 则抛出异常
     *
     * @param target 目标
     * @param arrs   数组
     */
    public static void requireContainsTargetInArrs(Object target, Object... arrs) {
        if (!Arrays.asList(arrs).contains(target)) {
            ExceptionUtil.throwRuntimeException();
        }
    }

    public static String concat(String str1, String str2) {
        Objects.requireNonNull(str1);
        if (str2 != null && !"".equals(str2)) {
            return str1 + str2;
        }
        return str1;
    }

}
