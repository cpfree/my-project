package cn.cpf.mod.plugins.dbutils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

/**
 * 全局工具类
 *
 * @author makejava
 * @version 1.0.0
 * @since 2018/08/14 18:11
 */
@SuppressWarnings("unused")
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
     * 获取默认参数
     *
     * @return 参数
     */
    public static Map<String, Object> getDefaultParam() {
        // 系统设置
        Map<String, Object> param = new HashMap<>(20);
        param.put("tool", GlobalTool.getInstance());
        return param;
    }


    /**
     * 创建集合
     *
     * @param items 初始元素
     * @return 集合对象
     */
    public Set<?> newHashSet(Object... items) {
        return items == null ? new HashSet<>() : new HashSet<>(Arrays.asList(items));
    }

    /**
     * 创建列表
     *
     * @param items 初始元素
     * @return 列表对象
     */
    public List<?> newArrayList(Object... items) {
        return items == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(items));
    }

    /**
     * 创建有序Map
     *
     * @return map对象
     */
    public Map<?, ?> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    /**
     * 创建无须Map
     *
     * @return map对象
     */
    public Map<?, ?> newHashMap() {
        return new HashMap<>(16);
    }

    /**
     * 获取字段，私有属性一样强制访问
     *
     * @param fieldName 字段名
     * @return 字段值
     */
    public String getField(String fieldName) {
        return fieldName.toUpperCase();

    }

    /**
     * 无返回执行，用于消除返回值
     *
     * @param obj 接收执行返回值
     */
    public void call(Object... obj) {

    }

}
