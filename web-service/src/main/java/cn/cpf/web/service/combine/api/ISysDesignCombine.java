package cn.cpf.web.service.combine.api;

import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.service.mod.system.dict.FieldDictUtils;
import com.github.cosycode.codedict.dynamic.DictTypeBean;

import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 10:05
 **/
public interface ISysDesignCombine {

    /**
     * 通过表名称获取表数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    SysTableBo selectSysTableBoByTableName(String tableName);

    /**
     * 通过表名称获取表字段数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    List<SysFieldBo> selectSysFieldBoByTableName(String tableName);

    List<DictItemDto> queryDictItemStructure();

    /**
     * 通过 fieldKey 查询 字段扩展表中的字段配置的 dict_type 相关的 dictItem
     * 并按照 fieldKey -> dictType 的形式封装
     *
     * @param fieldKeyList 字段标识 {@link FieldDictUtils#convertFieldKey(java.lang.String, java.lang.String)}
     */
    Map<String, DictTypeBean> queryDictItemByDictType(List<String> fieldKeyList);

    /**
     * 通过 fieldKey 查询 字段扩展表中的字段配置的 dict_type 相关的 dictItem
     *
     * @param fieldKey 字段标识 {@link FieldDictUtils#convertFieldKey(java.lang.String, java.lang.String)}
     */
    DictTypeBean queryOneDictTypeBean(String fieldKey);

    /**
     * 通过 fieldKey 查询 字段扩展表中的字段配置的 dict_type 相关的 dictItem
     * 并按照 fieldKey -> dictType 的形式封装
     */
    Map<String, DictTypeBean> queryAllDictItem();

    /**
     * @return 查询field -> dictType 映射
     */
    Map<String, String> queryFieldDictMapping(String... fieldKey);

    /**
     * @return 查询field -> dictType 映射
     */
    Map<String, String> queryAllFieldDictMapping();

}
