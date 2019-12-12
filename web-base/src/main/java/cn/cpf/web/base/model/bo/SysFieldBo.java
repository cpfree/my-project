package cn.cpf.web.base.model.bo;

import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.entity.SysDictType;
import cn.cpf.web.base.model.entity.SysFieldExtend;
import lombok.Data;

import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/11 13:37
 **/
@Data
public class SysFieldBo extends SysFieldExtend {

    /**
     * 数据表默认位置
     */
    private int ordinalPosition;
    /**
     * 字段默认值
     */
    private String columnDefault;
    /**
     * 是否为空
     */
    private String isNullable;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字符最大长度
     */
    private int characterMaximumLength;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 字段主键状态
     */
    private String columnKey;
    /**
     * 字段信息
     */
    private String columnComment;
    /**
     * 字典类型
     */
    private SysDictType sysDictType;
    /**
     * 字典项类型
     */
    private List<SysDictItem> sysDictItemList;
}
