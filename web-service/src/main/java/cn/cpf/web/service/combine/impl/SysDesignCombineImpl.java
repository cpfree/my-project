package cn.cpf.web.service.combine.impl;

import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.dal.combine.SysDesignCombineMapper;
import cn.cpf.web.service.combine.api.ISysDesignCombine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/12 14:08
 **/
@Service
@Slf4j
public class SysDesignCombineImpl implements ISysDesignCombine {

    @Autowired
    public SysDesignCombineMapper sysDesignCombineMapper;

    /**
     * 通过表名称获取表数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    @Override
    public SysTableBo selectSysTableBoByTableName(String tableName) {
        return sysDesignCombineMapper.selectSysTableBoByTableName(tableName);
    }

    /**
     * 通过表名称获取表字段数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    @Override
    public List<SysFieldBo> selectSysFieldBoByTableName(String tableName) {
        return sysDesignCombineMapper.selectSysFieldBoByTableName(tableName);
    }

    /**
     * @return
     */
    @Override
    public List<DictItemDto> queryDictItemStructure() {
        return sysDesignCombineMapper.queryDictItemStructure();
    }

}
