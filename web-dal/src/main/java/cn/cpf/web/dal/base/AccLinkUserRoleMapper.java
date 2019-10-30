package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.AccLinkUserRole;
import cn.cpf.web.base.model.example.AccLinkUserRoleExample;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface AccLinkUserRoleMapper {
    long countByExample(AccLinkUserRoleExample example);

    int deleteByExample(AccLinkUserRoleExample example);

    int deleteByPrimaryKey(String guid);

    int insert(AccLinkUserRole record);

    int insertSelective(AccLinkUserRole record);

    List<AccLinkUserRole> selectByExample(AccLinkUserRoleExample example);

    AccLinkUserRole selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") AccLinkUserRole record, @Param("example") AccLinkUserRoleExample example);

    int updateByExample(@Param("record") AccLinkUserRole record, @Param("example") AccLinkUserRoleExample example);

    int updateByPrimaryKeySelective(AccLinkUserRole record);

    int updateByPrimaryKey(AccLinkUserRole record);

    @Select("select role_key from acc_link_user_role where user_guid = #{userGuid} and state = 'y'")
    @ResultType(String.class)
    Set<String> selectAllActiveRoleNameByUserGuid(@Param("userGuid") String userGuid);
}