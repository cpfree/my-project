package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.entity.SysDictType;
import cn.cpf.web.base.model.example.SysDictTypeExample;
import java.util.List;
import java.util.Map;

/**
 * 系统字典表(SysDictType)表服务接口
 *
 * @author CPF
 * @since 2019-12-05 17:09:22
 */
public interface ISysDictType {
          
    /**
     * 通过 name 查询单条数据
     *
     * @param name 字典名称, 唯一标识, sys_dict_item关联字段, 不能顺便更改
     * @return 实例对象
     */
    SysDictType findByPrimaryKey(String name);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<SysDictType> selectByExample(SysDictTypeExample example);
    
    /**
     * 新增数据
     *
     * @param sysDictType 实例对象
     * @return 实例对象
     */
    SysDictType insert(SysDictType sysDictType);

    /**
     * 修改数据
     *
     * @param sysDictType 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(SysDictType sysDictType);

    /**
     * 通过主键删除数据
     *
     * @param name 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String name);

}