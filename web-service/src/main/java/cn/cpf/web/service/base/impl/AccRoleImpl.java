package cn.cpf.web.service.base.impl;

import cn.cpf.web.dal.base.AccRoleMapper;
import cn.cpf.web.base.model.entity.AccRole;
import cn.cpf.web.base.model.example.AccRoleExample;
import cn.cpf.web.service.base.api.IAccRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色表, 缩写role(AccRole)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class AccRoleImpl implements IAccRole {
    @Resource
    private AccRoleMapper accRoleMapper;
            
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    @Override
    public AccRole findByPrimaryKey(String guid) {
        return accRoleMapper.selectByPrimaryKey(guid);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<AccRole> selectByExample(AccRoleExample example) {
        return accRoleMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param accRole 实例对象
     * @return 实例对象
     */
    @Override
    public AccRole insert(AccRole accRole) {
        accRoleMapper.insert(accRole);
        return accRole;
    }

    /**
     * 修改数据
     *
     * @param accRole 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(AccRole accRole) {
        return accRoleMapper.updateByPrimaryKeySelective(accRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String guid) {
        return accRoleMapper.deleteByPrimaryKey(guid) > 0;
    }
}