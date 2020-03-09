package cn.cpf.web.service.combine.api;

import cn.cpf.web.base.lang.dict.DictTypeBean;
import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.dto.DictItemDto;

import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 10:05
 **/
public interface ISysDesignCombine {

    /**
     * 通过表名称获取表数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    SysTableBo selectSysTableBoByTableName(String tableName);

    /**
     * 通过表名称获取表字段数据
     *
     * @param tableName 表名称
     * @return 角色名称
     */
    List<SysFieldBo> selectSysFieldBoByTableName(String tableName);

    /**
     * @return
     */
    List<DictItemDto> queryDictItemStructure();

    /**
     * @return
     */
    Map<String, DictTypeBean> queryDictItem(String... fieldTag);

}
