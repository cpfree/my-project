package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.AccRole;
import cn.cpf.web.base.model.example.AccRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccRoleMapper {
    long countByExample(AccRoleExample example);

    int deleteByExample(AccRoleExample example);

    int deleteByPrimaryKey(String key);

    int insert(AccRole record);

    int insertSelective(AccRole record);

    List<AccRole> selectByExample(AccRoleExample example);

    AccRole selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") AccRole record, @Param("example") AccRoleExample example);

    int updateByExample(@Param("record") AccRole record, @Param("example") AccRoleExample example);

    int updateByPrimaryKeySelective(AccRole record);

    int updateByPrimaryKey(AccRole record);
}