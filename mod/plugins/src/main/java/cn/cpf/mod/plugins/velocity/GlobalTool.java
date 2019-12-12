package cn.cpf.mod.plugins.velocity;

import cn.cpf.web.base.util.common.StrUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * 全局工具类
 *
 * @author makejava
 * @version 1.0.0
 * @since 2018/08/14 18:11
 */
public class GlobalTool {

    private static volatile GlobalTool globalTool;

    /**
     * Jackson对象
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 私有构造方法
     */
    private GlobalTool() {
    }

    /**
     * 单例模式
     */
    public static GlobalTool getInstance() {
        if (globalTool == null) {
            synchronized (GlobalTool.class) {
                if (globalTool == null) {
                    globalTool = new GlobalTool();
                }
            }
        }
        return globalTool;
    }


    /**
     * 创建集合
     *
     * @param items 初始元素
     * @return 集合对象
     */
    public static Set<?> newHashSet(Object... items) {
        return items == null ? new HashSet<>() : new HashSet<>(Arrays.asList(items));
    }

    /**
     * 创建列表
     *
     * @param items 初始元素
     * @return 列表对象
     */
    public static List<?> newArrayList(Object... items) {
        return items == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(items));
    }

    /**
     * 创建有序Map
     *
     * @return map对象
     */
    public static Map<?, ?> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * 创建无须Map
     *
     * @return map对象
     */
    public static Map<?, ?> newHashMap() {
        return new HashMap<>(16);
    }

    public static String lowerCamelize(String field) {
        return StrUtils.lowerCamelize(field);
    }

    public static String upperCamelize(String field) {
        return StrUtils.upperCamelize(field);
    }


    public static String Upperize(String field) {
        return "5555";
    }


    /**
     * 无返回执行，用于消除返回值
     *
     * @param obj 接收执行返回值
     */
    public static void call(Object... obj) {

    }

}
