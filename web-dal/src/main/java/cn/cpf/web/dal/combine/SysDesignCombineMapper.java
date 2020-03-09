package cn.cpf.web.dal.combine;

import cn.cpf.web.base.lang.base.Record;
import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.base.model.entity.SysDictItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysDesignCombineMapper {

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

    /**
     * @return
     */
    @Select("select par_value parValue, value, cn_label label, ord from sys_dict_item where type = 'sys_dict_type_package' union all select tag parValue, name value, label, ord from sys_dict_type order by ord")
    List<DictItemDto> queryDictItemStructure();

    /**
     * @return
     */
    List<SysDictItem> queryDictItem(@Param("ids") List<Record> ids);

}