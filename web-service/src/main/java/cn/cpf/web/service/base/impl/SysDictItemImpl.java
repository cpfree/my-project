package cn.cpf.web.service.base.impl;

import cn.cpf.web.base.model.bo.DictItem;
import cn.cpf.web.base.model.entity.SysDictItemKey;
import cn.cpf.web.dal.base.SysDictItemMapper;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.example.SysDictItemExample;
import cn.cpf.web.service.base.api.ISysDictItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统字典项表(SysDictItem)表服务实现类
 *
 * @author CPF
 * @since 2019-12-03 20:30:22
 */
@Service
public class SysDictItemImpl implements ISysDictItem {
    @Resource
    private SysDictItemMapper sysDictItemMapper;

    /**
     * 通过 guid 查询单条数据
     *
     * @param sysDictItemKey 主键对象
     * @return 实例对象
     */
    @Override
    public SysDictItem findByPrimaryKey(SysDictItemKey sysDictItemKey) {
        return sysDictItemMapper.selectByPrimaryKey(sysDictItemKey);
    }

    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<SysDictItem> selectByExample(SysDictItemExample example) {
        return sysDictItemMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param sysDictItem 实例对象
     * @return 实例对象
     */
    @Override
    public SysDictItem insert(SysDictItem sysDictItem) {
        sysDictItemMapper.insert(sysDictItem);
        return sysDictItem;
    }

    /**
     * 修改数据
     *
     * @param sysDictItem 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(SysDictItem sysDictItem) {
        return sysDictItemMapper.updateByPrimaryKeySelective(sysDictItem);
    }

    /**
     * 通过主键删除数据
     *
     * @param sysDictItemKey 主键对象
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(SysDictItemKey sysDictItemKey) {
        return sysDictItemMapper.deleteByPrimaryKey(sysDictItemKey) > 0;
    }

    /**
     * 查询列表数据
     *
     * @param dictType 字典类型
     * @return 对象列表
     */
    @Override
    public List<SysDictItem> selectByDictType(String dictType) {
        SysDictItemExample example = new SysDictItemExample();
        example.createCriteria().andTypeEqualTo(dictType);
        return sysDictItemMapper.selectByExample(example);
    }

    /**
     * 查询列表数据
     *
     * @param dictType 字典类型
     * @return 对象列表
     */
    @Override
    public List<DictItem> queryDictItem(String dictType) {
        return selectByDictType(dictType).stream().map(DictItem::new).collect(Collectors.toList());
    }

    /**
     * 查询列表数据
     *
     * @return 对象列表
     */
    @Override
    public List<DictItem> queryDictItemStructure() {
        return sysDictItemMapper.queryDictItemStructure();
    }

    /**
     * 查询列表数据
     *
     * @param dictTypeList 字典类型
     * @return 对象列表
     */
    @Override
    public Map<String, List<SysDictItem>> selectByDictType(List<String> dictTypeList) {
        SysDictItemExample example = new SysDictItemExample();
        example.createCriteria().andTypeIn(dictTypeList);
        final List<SysDictItem> sysDictItems = sysDictItemMapper.selectByExample(example);
        return sysDictItems.stream().collect(Collectors.groupingBy(SysDictItem::getValue));
    }

}