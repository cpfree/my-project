package cn.cpf.web.base.model.bo;

import cn.cpf.web.base.model.entity.SysDictItem;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/5 17:35
 **/
@Data
@NoArgsConstructor
public class DictItem {

    public DictItem(SysDictItem sysDictItem) {
        this.pValue = sysDictItem.getpValue();
        this.value = sysDictItem.getValue();
        this.label = sysDictItem.getLabel();
        this.sort = sysDictItem.getSort();
    }

    private String pValue;

    private String value;

    private String label;

    private Integer sort;

}
