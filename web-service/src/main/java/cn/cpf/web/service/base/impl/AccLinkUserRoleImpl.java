package cn.cpf.web.service.base.impl;

import cn.cpf.web.dal.base.AccLinkUserRoleMapper;
import cn.cpf.web.base.model.entity.AccLinkUserRole;
import cn.cpf.web.base.model.example.AccLinkUserRoleExample;
import cn.cpf.web.service.base.api.IAccLinkUserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户角色关系表(AccLinkUserRole)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class AccLinkUserRoleImpl implements IAccLinkUserRole {
    @Resource
    private AccLinkUserRoleMapper accLinkUserRoleMapper;
            
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    @Override
    public AccLinkUserRole findByPrimaryKey(String guid) {
        return accLinkUserRoleMapper.selectByPrimaryKey(guid);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<AccLinkUserRole> selectByExample(AccLinkUserRoleExample example) {
        return accLinkUserRoleMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param accLinkUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public AccLinkUserRole insert(AccLinkUserRole accLinkUserRole) {
        accLinkUserRoleMapper.insert(accLinkUserRole);
        return accLinkUserRole;
    }

    /**
     * 修改数据
     *
     * @param accLinkUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(AccLinkUserRole accLinkUserRole) {
        return accLinkUserRoleMapper.updateByPrimaryKeySelective(accLinkUserRole);
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String guid) {
        return accLinkUserRoleMapper.deleteByPrimaryKey(guid) > 0;
    }
}