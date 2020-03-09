package cn.cpf.web.service.combine.impl;

import cn.cpf.web.base.lang.base.Record;
import cn.cpf.web.base.lang.dict.DictItemBean;
import cn.cpf.web.base.lang.dict.DictTypeBean;
import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.dal.base.SysFieldExtendMapper;
import cn.cpf.web.dal.combine.SysDesignCombineMapper;
import cn.cpf.web.service.combine.api.ISysDesignCombine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

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
    @Autowired
    public SysFieldExtendMapper sysFieldExtendMapper;

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

    /**
     * @param typeArr 表名加列名
     */
    @Override
    public Map<String, DictTypeBean> queryDictItem(String... typeArr) {
        final List<Record> params = Arrays.stream(typeArr).map(it -> {
            final String[] split = it.split("#");
            Record record = new Record();
            record.put("table", split[0]);
            record.put("name", split[1]);
            return record;
        }).collect(Collectors.toList());
        final List<SysDictItem> sysDictItemList = sysDesignCombineMapper.queryDictItem(params);
        final Map<String, ConcurrentMap<String, DictItemBean>> collect = sysDictItemList.stream()
                .collect(Collectors.groupingBy(SysDictItem::getType, Collectors.toConcurrentMap(SysDictItem::getName, DictItemBean::of)));
        return collect.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, it -> new DictTypeBean(it.getKey(), it.getValue())));
    }

}
