package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.entity.SysDictItemKey;
import cn.cpf.web.base.model.example.SysDictItemExample;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统字典项表(SysDictItem)表服务接口
 *
 * @author CPF
 * @since 2019-12-03 20:30:21
 */
public interface ISysDictItem {
          
    /**
     * 通过 guid 查询单条数据
     *
     * @param sysDictItemKey 主键对象
     * @return 实例对象
     */
    SysDictItem findByPrimaryKey(SysDictItemKey sysDictItemKey);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<SysDictItem> selectByExample(SysDictItemExample example);
    
    /**
     * 新增数据
     *
     * @param sysDictItem 实例对象
     * @return 实例对象
     */
    SysDictItem insert(SysDictItem sysDictItem);

    /**
     * 修改数据
     *
     * @param sysDictItem 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(SysDictItem sysDictItem);

    /**
     * 通过主键删除数据
     *
     * @param sysDictItemKey 主键对象
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(SysDictItemKey sysDictItemKey);

    /**
     * 查询列表数据
     *
     * @param dictType 字典类型
     * @return 对象列表
     */
    List<SysDictItem> selectByDictType(String dictType);

    /**
     * 查询列表数据
     *
     * @param dictType 字典类型
     * @return 对象列表
     */
    List<DictItemDto> queryDictItem(String dictType);

    /**
     * 查询列表数据
     *
     * @param dictTypeList 字典类型
     * @return 对象列表
     */
    Map<String, List<SysDictItem>> selectByDictType(Set<String> dictTypeList);

}