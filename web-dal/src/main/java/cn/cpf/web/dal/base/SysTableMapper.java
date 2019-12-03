package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.SysTable;
import cn.cpf.web.base.model.example.SysTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysTableMapper {
    long countByExample(SysTableExample example);

    int deleteByExample(SysTableExample example);

    int deleteByPrimaryKey(String guid);

    int insert(SysTable record);

    int insertSelective(SysTable record);

    List<SysTable> selectByExample(SysTableExample example);

    SysTable selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") SysTable record, @Param("example") SysTableExample example);

    int updateByExample(@Param("record") SysTable record, @Param("example") SysTableExample example);

    int updateByPrimaryKeySelective(SysTable record);

    int updateByPrimaryKey(SysTable record);
}