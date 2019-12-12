package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.example.SysDictItemExample;
import cn.cpf.web.base.model.entity.SysDictItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDictItemMapper {
    long countByExample(SysDictItemExample example);

    int deleteByExample(SysDictItemExample example);

    int deleteByPrimaryKey(SysDictItemKey key);

    int insert(SysDictItem record);

    int insertSelective(SysDictItem record);

    List<SysDictItem> selectByExample(SysDictItemExample example);

    SysDictItem selectByPrimaryKey(SysDictItemKey key);

    int updateByExampleSelective(@Param("record") SysDictItem record, @Param("example") SysDictItemExample example);

    int updateByExample(@Param("record") SysDictItem record, @Param("example") SysDictItemExample example);

    int updateByPrimaryKeySelective(SysDictItem record);

    int updateByPrimaryKey(SysDictItem record);
}