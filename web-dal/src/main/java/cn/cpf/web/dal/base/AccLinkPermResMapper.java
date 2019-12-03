package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.AccLinkPermRes;
import cn.cpf.web.base.model.example.AccLinkPermResExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface AccLinkPermResMapper {
    long countByExample(AccLinkPermResExample example);

    int deleteByExample(AccLinkPermResExample example);

    int deleteByPrimaryKey(String guid);

    int insert(AccLinkPermRes record);

    int insertSelective(AccLinkPermRes record);

    List<AccLinkPermRes> selectByExample(AccLinkPermResExample example);

    AccLinkPermRes selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") AccLinkPermRes record, @Param("example") AccLinkPermResExample example);

    int updateByExample(@Param("record") AccLinkPermRes record, @Param("example") AccLinkPermResExample example);

    int updateByPrimaryKeySelective(AccLinkPermRes record);

    int updateByPrimaryKey(AccLinkPermRes record);
}