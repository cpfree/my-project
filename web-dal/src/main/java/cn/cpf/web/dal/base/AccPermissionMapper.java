package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.AccPermission;
import cn.cpf.web.base.model.example.AccPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccPermissionMapper {
    long countByExample(AccPermissionExample example);

    int deleteByExample(AccPermissionExample example);

    int deleteByPrimaryKey(String key);

    int insert(AccPermission record);

    int insertSelective(AccPermission record);

    List<AccPermission> selectByExample(AccPermissionExample example);

    AccPermission selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") AccPermission record, @Param("example") AccPermissionExample example);

    int updateByExample(@Param("record") AccPermission record, @Param("example") AccPermissionExample example);

    int updateByPrimaryKeySelective(AccPermission record);

    int updateByPrimaryKey(AccPermission record);
}