package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.base.model.example.AccUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccUserMapper {
    long countByExample(AccUserExample example);

    int deleteByExample(AccUserExample example);

    int deleteByPrimaryKey(String guid);

    int insert(AccUser record);

    int insertSelective(AccUser record);

    List<AccUser> selectByExample(AccUserExample example);

    AccUser selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") AccUser record, @Param("example") AccUserExample example);

    int updateByExample(@Param("record") AccUser record, @Param("example") AccUserExample example);

    int updateByPrimaryKeySelective(AccUser record);

    int updateByPrimaryKey(AccUser record);
}