package cn.cpf.web.service.base.impl;

import cn.cpf.web.dal.base.AccResourceMapper;
import cn.cpf.web.base.model.entity.AccResource;
import cn.cpf.web.base.model.example.AccResourceExample;
import cn.cpf.web.service.base.api.IAccResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 可访问的资源表, 缩写 res(AccResource)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class AccResourceImpl implements IAccResource {
    @Resource
    private AccResourceMapper accResourceMapper;
            
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    @Override
    public AccResource findByPrimaryKey(String guid) {
        return accResourceMapper.selectByPrimaryKey(guid);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<AccResource> selectByExample(AccResourceExample example) {
        return accResourceMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param accResource 实例对象
     * @return 实例对象
     */
    @Override
    public AccResource insert(AccResource accResource) {
        accResourceMapper.insert(accResource);
        return accResource;
    }

    /**
     * 修改数据
     *
     * @param accResource 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(AccResource accResource) {
        return accResourceMapper.updateByPrimaryKeySelective(accResource);
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String guid) {
        return accResourceMapper.deleteByPrimaryKey(guid) > 0;
    }
}