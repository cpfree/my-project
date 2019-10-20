package cn.cpf.web.boot.dao.base;

import cn.cpf.web.base.model.entity.InGirl;
import cn.cpf.web.base.model.entity.InGirlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InGirlMapper {
    long countByExample(InGirlExample example);

    int deleteByExample(InGirlExample example);

    int deleteByPrimaryKey(String id);

    int insert(InGirl record);

    int insertSelective(InGirl record);

    List<InGirl> selectByExample(InGirlExample example);

    InGirl selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InGirl record, @Param("example") InGirlExample example);

    int updateByExample(@Param("record") InGirl record, @Param("example") InGirlExample example);

    int updateByPrimaryKeySelective(InGirl record);

    int updateByPrimaryKey(InGirl record);
}