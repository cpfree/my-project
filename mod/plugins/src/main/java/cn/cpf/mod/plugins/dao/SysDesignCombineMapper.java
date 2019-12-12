package cn.cpf.mod.plugins.dao;

import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;

import java.util.List;

public interface SysDesignCombineMapper {

    /**
     * 通过表名称获取表数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    SysTableBo selectSysTableBoByTableName(String tableName);

    /**
     * 通过表名称获取表字段数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    List<SysFieldBo> selectSysFieldBoByTableName(String tableName);

}