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

    private String parValue;

    private String value;

    private String label;

    private String enLabel;

    private String cnLabel;

    private Integer sort;

    public static DictItemDto of(SysDictItem sysDictItem) {
        DictItemDto dto = new DictItemDto();
        dto.setParValue(sysDictItem.getParValue());
        dto.setValue(sysDictItem.getValue());
        dto.setEnLabel(sysDictItem.getEnLabel());
        dto.setCnLabel(sysDictItem.getCnLabel());
        dto.setSort(sysDictItem.getOrd());
        return dto;
    }

    public static DictItemDto enOf(SysDictItem sysDictItem) {
        final DictItemDto of = of(sysDictItem);
        of.setLabel(sysDictItem.getEnLabel());
        return of;
    }

    public static DictItemDto cnOf(SysDictItem sysDictItem) {
        final DictItemDto of = of(sysDictItem);
        of.setLabel(sysDictItem.getCnLabel());
        return of;
    }

}
