package cn.cpf.web.service.combine.impl;

import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.entity.SysFieldExtend;
import cn.cpf.web.base.model.entity.SysFieldExtendKey;
import cn.cpf.web.base.model.example.SysFieldExtendExample;
import cn.cpf.web.dal.base.SysFieldExtendMapper;
import cn.cpf.web.dal.combine.SysDesignCombineMapper;
import cn.cpf.web.service.combine.api.ISysDesignCombine;
import cn.cpf.web.service.mod.system.dict.FieldDictUtils;
import com.github.cosycode.codedict.dynamic.DictTypeBean;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public Map<String, DictTypeBean> queryDictItemByDictType(List<String> fieldKeyList) {
        if (CollectionUtils.isEmpty(fieldKeyList)) {
            return Collections.emptyMap();
        }
        final List<SysFieldExtendKey> params = fieldKeyList.stream().map(FieldDictUtils::convertFieldKeyFromFieldKey).collect(Collectors.toList());
        // 从数据库查询数据
        final List<SysDictItem> sysDictItemList = sysDesignCombineMapper.queryDictItem(params);
        return FieldDictUtils.convertTypeMapFromItem(sysDictItemList);
    }

    @Override
    public DictTypeBean queryOneDictTypeBean(@NonNull String fieldKey) {
        if (StringUtils.isBlank(fieldKey)) {
            return DictTypeBean.NULL_OBJECT;
        }
        SysFieldExtendKey key = FieldDictUtils.convertFieldKeyFromFieldKey(fieldKey);
        // 从数据库查询数据
        final List<SysDictItem> sysDictItemList = sysDesignCombineMapper.queryDictItem(Collections.singletonList(key));
        return FieldDictUtils.convertTypeFromItem(sysDictItemList);
    }

    @Override
    public Map<String, DictTypeBean> queryAllDictItem() {
        final List<SysDictItem> sysDictItemList = sysDesignCombineMapper.queryDictItem(Collections.emptyList());
        return FieldDictUtils.convertTypeMapFromItem(sysDictItemList);
    }

    /**
     * 从数据库查询数据
     *
     * @param fieldKey 表名加列名
     */
    @Override
    public Map<String, String> queryFieldDictMapping(String... fieldKey) {
        throw new RuntimeException("TODO");
    }

    /**
     * @return 查询field -> dictType 映射
     */
    @Override
    public Map<String, String> queryAllFieldDictMapping() {
        SysFieldExtendExample example = new SysFieldExtendExample();
        example.createCriteria().andDictTypeIsNotNull();
        List<SysFieldExtend> sysFieldExtends = sysFieldExtendMapper.selectByExample(example);
        return sysFieldExtends.stream().collect(Collectors.toMap(it -> FieldDictUtils.convertFieldKey(it.getTableName(), it.getName()), SysFieldExtend::getDictType));
    }

}
