package cn.cpf.web.base.model.dto;

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
public class DictItemDto  {

    private String pValue;

    private String value;

    private String label;

    private Integer sort;

    public static DictItemDto enOf(SysDictItem sysDictItem) {
        DictItemDto dto = new DictItemDto();
        dto.setPValue(sysDictItem.getPValue());
        dto.setValue(sysDictItem.getValue());
        dto.setLabel(sysDictItem.getEnLabel());
        dto.setSort(sysDictItem.getSort());
        return dto;
    }

    public static DictItemDto cnOf(SysDictItem sysDictItem) {
        DictItemDto dto = new DictItemDto();
        dto.setPValue(sysDictItem.getPValue());
        dto.setValue(sysDictItem.getValue());
        dto.setLabel(sysDictItem.getCnLabel());
        dto.setSort(sysDictItem.getSort());
        return dto;
    }

}
