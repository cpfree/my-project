package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.AccResource;
import cn.cpf.web.base.model.example.AccResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccResourceMapper {
    long countByExample(AccResourceExample example);

    int deleteByExample(AccResourceExample example);

    int deleteByPrimaryKey(String key);

    int insert(AccResource record);

    int insertSelective(AccResource record);

    List<AccResource> selectByExample(AccResourceExample example);

    AccResource selectByPrimaryKey(String key);

    int updateByExampleSelective(@Param("record") AccResource record, @Param("example") AccResourceExample example);

    int updateByExample(@Param("record") AccResource record, @Param("example") AccResourceExample example);

    int updateByPrimaryKeySelective(AccResource record);

    int updateByPrimaryKey(AccResource record);
}