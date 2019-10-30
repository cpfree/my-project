package cn.cpf.web.service.base.impl;

import cn.cpf.web.dal.base.AccPermissionMapper;
import cn.cpf.web.base.model.entity.AccPermission;
import cn.cpf.web.base.model.example.AccPermissionExample;
import cn.cpf.web.service.base.api.IAccPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权限表, 缩写 perm(AccPermission)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class AccPermissionImpl implements IAccPermission {
    @Resource
    private AccPermissionMapper accPermissionMapper;
            
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    @Override
    public AccPermission findByPrimaryKey(String guid) {
        return accPermissionMapper.selectByPrimaryKey(guid);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<AccPermission> selectByExample(AccPermissionExample example) {
        return accPermissionMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param accPermission 实例对象
     * @return 实例对象
     */
    @Override
    public AccPermission insert(AccPermission accPermission) {
        accPermissionMapper.insert(accPermission);
        return accPermission;
    }

    /**
     * 修改数据
     *
     * @param accPermission 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(AccPermission accPermission) {
        return accPermissionMapper.updateByPrimaryKeySelective(accPermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String guid) {
        return accPermissionMapper.deleteByPrimaryKey(guid) > 0;
    }
}