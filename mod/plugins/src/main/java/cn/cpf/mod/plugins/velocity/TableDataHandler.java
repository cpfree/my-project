package cn.cpf.mod.plugins.velocity;

import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.entity.SysDictItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b> 用于生成模板文件
 *
 * @author CPF
 * @date 2019/12/3 16:50
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableDataHandler {

    /**
     * 表数据信息
     */
    private SysTableBo sysTable;
    /**
     * 表字段信息
     */
    private List<SysFieldBo> sysFieldList;

    /**
     * 字典信息map
     */
    Map<String, List<SysDictItem>> dictItemListMap;

    /**
     * 模板信息
     */
    private List<VelocityGeneInfoBean> modalInfoList;

}
