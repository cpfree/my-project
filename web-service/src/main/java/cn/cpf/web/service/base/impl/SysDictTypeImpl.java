package cn.cpf.web.service.base.impl;

import cn.cpf.web.base.model.entity.SysDictType;
import cn.cpf.web.base.model.example.SysDictTypeExample;
import cn.cpf.web.dal.base.SysDictTypeMapper;
import cn.cpf.web.service.base.api.ISysDictType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统字典表(SysDictType)表服务实现类
 *
 * @author CPF
 * @since 2019-12-05 17:09:23
 */
@Service
public class SysDictTypeImpl implements ISysDictType {
    @Resource
    private SysDictTypeMapper sysDictTypeMapper;
            
    /**
     * 通过 name 查询单条数据
     *
     * @param name 字典名称, 唯一标识, sys_dict_item关联字段, 不能顺便更改
     * @return 实例对象
     */
    @Override
    public SysDictType findByPrimaryKey(String name) {
        return sysDictTypeMapper.selectByPrimaryKey(name);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<SysDictType> selectByExample(SysDictTypeExample example) {
        return sysDictTypeMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param sysDictType 实例对象
     * @return 实例对象
     */
    @Override
    public SysDictType insert(SysDictType sysDictType) {
        sysDictTypeMapper.insert(sysDictType);
        return sysDictType;
    }

    /**
     * 修改数据
     *
     * @param sysDictType 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(SysDictType sysDictType) {
        return sysDictTypeMapper.updateByPrimaryKeySelective(sysDictType);
    }

    /**
     * 通过主键删除数据
     *
     * @param name 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String name) {
        return sysDictTypeMapper.deleteByPrimaryKey(name) > 0;
    }
}