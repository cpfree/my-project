package cn.cpf.web.base.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@NoArgsConstructor
@Data
public class SysDictItem extends SysDictItemKey {

    /**
     * 中文标签
     */
    private String cnLabel;

    /**
     * 英文标签
     */
    private String enLabel;

    private String pValue;

    private Integer sort;

    private Integer level;

    private String comment;

    private Date addTime;

    private Date updateTime;

    private String state;

}