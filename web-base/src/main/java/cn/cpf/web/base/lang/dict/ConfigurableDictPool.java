package cn.cpf.web.base.lang.dict;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/13 10:44
 **/
class ConfigurableDictPool {

    private ConfigurableDictPool() {
    }

    /**
     * 用于存储字典数据
     */
    private static final Map<String, DictTypeBean> typeBeanMap = new ConcurrentHashMap<>();

    /**
     * map中单例, 防止建立多个相同类型的 DictTypeBean
     *
     * @param fieldKey 字段key
     */
    private static @NotNull DictTypeBean getFromMap(String fieldKey) {
        DictTypeBean dictTypeBean = typeBeanMap.get(fieldKey);
        if (dictTypeBean == null) {
            synchronized (typeBeanMap) {
                dictTypeBean = typeBeanMap.computeIfAbsent(fieldKey, key -> new DictTypeBean());
            }
        }
        return dictTypeBean;
    }

    /**
     * @param map
     */
    public static void putAllType(Map<String, DictTypeBean> map) {
        typeBeanMap.putAll(map);
    }

    public static void putType(String fieldKey, DictTypeBean bean) {
        typeBeanMap.put(fieldKey, bean);
    }

    public static DictTypeBean getType(String type) {
        return typeBeanMap.get(type);
    }

    public static void putType(IDictItem iDictItem, DictTypeBean bean) {
        putType(iDictItem.fieldKey(), bean);
    }

    /**
     * 往 map 中添加代码项
     */
    public static void putItem(IDictItem iDictItem, DictItemBean dictItemBean) {
        DictTypeBean dictTypeBean = getFromMap(iDictItem.fieldKey());
        dictTypeBean.addDictItem(iDictItem, dictItemBean);
    }

    static DictItemBean getItem(IDictItem iDictItem) {
        return typeBeanMap.get(iDictItem.fieldKey()).getDictItemBean(iDictItem);
    }

}
