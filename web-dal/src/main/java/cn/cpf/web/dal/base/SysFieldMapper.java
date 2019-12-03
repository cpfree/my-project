package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.SysField;
import cn.cpf.web.base.model.example.SysFieldExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFieldMapper {
    long countByExample(SysFieldExample example);

    int deleteByExample(SysFieldExample example);

    int deleteByPrimaryKey(String guid);

    int insert(SysField record);

    int insertSelective(SysField record);

    List<SysField> selectByExample(SysFieldExample example);

    SysField selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") SysField record, @Param("example") SysFieldExample example);

    int updateByExample(@Param("record") SysField record, @Param("example") SysFieldExample example);

    int updateByPrimaryKeySelective(SysField record);

    int updateByPrimaryKey(SysField record);
}