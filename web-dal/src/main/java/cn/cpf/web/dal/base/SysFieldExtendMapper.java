package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.SysFieldExtend;
import cn.cpf.web.base.model.example.SysFieldExtendExample;
import cn.cpf.web.base.model.entity.SysFieldExtendKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFieldExtendMapper {
    long countByExample(SysFieldExtendExample example);

    int deleteByExample(SysFieldExtendExample example);

    int deleteByPrimaryKey(SysFieldExtendKey key);

    int insert(SysFieldExtend record);

    int insertSelective(SysFieldExtend record);

    List<SysFieldExtend> selectByExample(SysFieldExtendExample example);

    SysFieldExtend selectByPrimaryKey(SysFieldExtendKey key);

    int updateByExampleSelective(@Param("record") SysFieldExtend record, @Param("example") SysFieldExtendExample example);

    int updateByExample(@Param("record") SysFieldExtend record, @Param("example") SysFieldExtendExample example);

    int updateByPrimaryKeySelective(SysFieldExtend record);

    int updateByPrimaryKey(SysFieldExtend record);

}