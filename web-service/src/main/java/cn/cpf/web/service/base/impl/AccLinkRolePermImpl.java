package cn.cpf.web.service.base.impl;

import cn.cpf.web.dal.base.AccLinkRolePermMapper;
import cn.cpf.web.base.model.entity.AccLinkRolePerm;
import cn.cpf.web.base.model.example.AccLinkRolePermExample;
import cn.cpf.web.service.base.api.IAccLinkRolePerm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色权限关系表, 角色和权限的相关关系, 一个角色应该拥有哪些权限(AccLinkRolePerm)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class AccLinkRolePermImpl implements IAccLinkRolePerm {
    @Resource
    private AccLinkRolePermMapper accLinkRolePermMapper;
            
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    @Override
    public AccLinkRolePerm findByPrimaryKey(String guid) {
        return accLinkRolePermMapper.selectByPrimaryKey(guid);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<AccLinkRolePerm> selectByExample(AccLinkRolePermExample example) {
        return accLinkRolePermMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param accLinkRolePerm 实例对象
     * @return 实例对象
     */
    @Override
    public AccLinkRolePerm insert(AccLinkRolePerm accLinkRolePerm) {
        accLinkRolePermMapper.insert(accLinkRolePerm);
        return accLinkRolePerm;
    }

    /**
     * 修改数据
     *
     * @param accLinkRolePerm 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(AccLinkRolePerm accLinkRolePerm) {
        return accLinkRolePermMapper.updateByPrimaryKeySelective(accLinkRolePerm);
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String guid) {
        return accLinkRolePermMapper.deleteByPrimaryKey(guid) > 0;
    }
}