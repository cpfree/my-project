package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.DtInGirl;
import cn.cpf.web.base.model.example.DtInGirlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DtInGirlMapper {
    long countByExample(DtInGirlExample example);

    int deleteByExample(DtInGirlExample example);

    int deleteByPrimaryKey(String id);

    int insert(DtInGirl record);

    int insertSelective(DtInGirl record);

    List<DtInGirl> selectByExample(DtInGirlExample example);

    DtInGirl selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DtInGirl record, @Param("example") DtInGirlExample example);

    int updateByExample(@Param("record") DtInGirl record, @Param("example") DtInGirlExample example);

    int updateByPrimaryKeySelective(DtInGirl record);

    int updateByPrimaryKey(DtInGirl record);
}