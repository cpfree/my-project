package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.bo.DictItem;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.example.SysDictItemExample;
import cn.cpf.web.base.model.entity.SysDictItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysDictItemMapper {
    long countByExample(SysDictItemExample example);

    int deleteByExample(SysDictItemExample example);

    int deleteByPrimaryKey(SysDictItemKey key);

    int insert(SysDictItem record);

    int insertSelective(SysDictItem record);

    List<SysDictItem> selectByExample(SysDictItemExample example);

    SysDictItem selectByPrimaryKey(SysDictItemKey key);

    int updateByExampleSelective(@Param("record") SysDictItem record, @Param("example") SysDictItemExample example);

    int updateByExample(@Param("record") SysDictItem record, @Param("example") SysDictItemExample example);

    int updateByPrimaryKeySelective(SysDictItem record);

    int updateByPrimaryKey(SysDictItem record);

    @Select("select p_value pValue, value, level, sort from sys_dict_item where type = 'sys_dict_type_package'" +
            " union all select type pValue, name value, text level, sort from sys_dict_type order by sort")
    List<DictItem> queryDictItemStructure();
}