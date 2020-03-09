package cn.cpf.web.base.lang.dict;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <b>Description : </b> 用于缓存字典类型数据
 *
 * @author CPF
 * @date 2019/12/13 15:06
 **/
public class DictTypeBean {

    public DictTypeBean() {
        itemMap = new ConcurrentHashMap<>();
    }

    public DictTypeBean(String fieldKey, ConcurrentMap<String, DictItemBean> map) {
        this.fieldKey = fieldKey;
        itemMap = map;
    }

    /**
     * 存放字典项数据(key为数据字典项的name属性, )
     */
    @Getter
    private String fieldKey;
    /**
     * 存放字典项数据(key为数据字典项的name属性, )
     */
    private Map<String, DictItemBean> itemMap;

    public void addDictItem(IDictItem dictItem, DictItemBean dictItemBean) {
        itemMap.put(dictItem.name(), dictItemBean);
    }

    public DictItemBean getDictItemBean(IDictItem dictItem) {
        return itemMap.get(dictItem.name());
    }

    public Collection<DictItemBean> listItem() {
        return itemMap.values();
    }

}
