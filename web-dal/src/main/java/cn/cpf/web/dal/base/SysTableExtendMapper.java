package cn.cpf.web.dal.base;

import cn.cpf.web.base.model.entity.SysTableExtend;
import cn.cpf.web.base.model.example.SysTableExtendExample;
import cn.cpf.web.base.model.entity.SysTableExtendKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysTableExtendMapper {
    long countByExample(SysTableExtendExample example);

    int deleteByExample(SysTableExtendExample example);

    int deleteByPrimaryKey(SysTableExtendKey key);

    int insert(SysTableExtend record);

    int insertSelective(SysTableExtend record);

    List<SysTableExtend> selectByExample(SysTableExtendExample example);

    SysTableExtend selectByPrimaryKey(SysTableExtendKey key);

    int updateByExampleSelective(@Param("record") SysTableExtend record, @Param("example") SysTableExtendExample example);

    int updateByExample(@Param("record") SysTableExtend record, @Param("example") SysTableExtendExample example);

    int updateByPrimaryKeySelective(SysTableExtend record);

    int updateByPrimaryKey(SysTableExtend record);
}