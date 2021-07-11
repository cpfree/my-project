package cn.cpf.web.service.mod.system.dict;

import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.entity.SysDictItemKey;
import cn.cpf.web.base.model.entity.SysFieldExtendKey;
import com.github.cosycode.codedict.core.DictItemBean;
import com.github.cosycode.codedict.dynamic.DictTypeBean;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <b>Description : </b> 字段 和 数据字典转换类
 *
 * @author CPF
 * Date: 2020/6/16 14:12
 */
public class FieldDictUtils {

    public static List<DictTypeBean> convertTypeListFromItem(List<SysDictItem> dictItemList) {
        if (CollectionUtils.isEmpty(dictItemList)) {
            return Collections.emptyList();
        }
        Map<String, Map<String, DictItemBean>> collect = dictItemList.stream().collect(Collectors.groupingBy(SysDictItemKey::getType, Collectors.toMap(SysDictItemKey::getName, FieldDictUtils::convertItemFromSysItem)));

        return collect.entrySet().stream().map(it -> new DictTypeBean(it.getKey(), it.getValue())).collect(Collectors.toList());
    }

    public static Map<String, DictTypeBean> convertTypeMapFromItem(List<SysDictItem> dictItemList) {
        if (CollectionUtils.isEmpty(dictItemList)) {
            return Collections.emptyMap();
        }
        Map<String, Map<String, DictItemBean>> collect = dictItemList.stream().collect(Collectors.groupingBy(SysDictItemKey::getType, Collectors.toMap(SysDictItemKey::getName, FieldDictUtils::convertItemFromSysItem)));

        return collect.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, it -> new DictTypeBean(it.getKey(), it.getValue())));
    }

    public static DictTypeBean convertTypeFromItem(List<SysDictItem> dictItemList) {
        if (CollectionUtils.isEmpty(dictItemList)) {
            return DictTypeBean.NULL_OBJECT;
        }
        String type = dictItemList.get(0).getType();
        Map<String, DictItemBean> collect = dictItemList.stream().collect(Collectors.toMap(SysDictItemKey::getName, FieldDictUtils::convertItemFromSysItem));
        return new DictTypeBean(type, collect);
    }

    public static DictItemBean convertItemFromSysItem(SysDictItem sysDictItem) {
        DictItemBean dictItemBean = new DictItemBean();
        dictItemBean.setLabel(sysDictItem.getCnLabel());
        dictItemBean.setValue(sysDictItem.getValue());
        return dictItemBean;
    }

    /**
     * @param tableName 表名
     * @param fieldName 字段名
     * @return fieldKey
     */
    public static String convertFieldKey(String tableName, String fieldName) {
        return tableName + "#" + fieldName;
    }

    /**
     * @param fieldKey 字段标识
     * @return [表名, 字段名]
     */
    public static String[] convertFromFieldKey(String fieldKey) {
        return fieldKey.split("#");
    }

    /**
     * @param fieldKey 字段标识
     * @return [表名, 字段名]
     */
    public static SysFieldExtendKey convertFieldKeyFromFieldKey(String fieldKey) {
        String[] split = fieldKey.split("#");
        SysFieldExtendKey key = new SysFieldExtendKey();
        key.setSchemaTag("my-project");
        key.setTableName(split[0]);
        key.setName(split[1]);
        return key;
    }

}
