package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.AccLinkRolePerm;
import cn.cpf.web.base.model.example.AccLinkRolePermExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccLinkRolePermMapper {
    long countByExample(AccLinkRolePermExample example);

    int deleteByExample(AccLinkRolePermExample example);

    int deleteByPrimaryKey(String guid);

    int insert(AccLinkRolePerm record);

    int insertSelective(AccLinkRolePerm record);

    List<AccLinkRolePerm> selectByExample(AccLinkRolePermExample example);

    AccLinkRolePerm selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") AccLinkRolePerm record, @Param("example") AccLinkRolePermExample example);

    int updateByExample(@Param("record") AccLinkRolePerm record, @Param("example") AccLinkRolePermExample example);

    int updateByPrimaryKeySelective(AccLinkRolePerm record);

    int updateByPrimaryKey(AccLinkRolePerm record);
}