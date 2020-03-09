package cn.cpf.web.base.lang.dict;

import cn.cpf.web.base.model.entity.SysDictItem;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/13 10:43
 **/
@Data
@NoArgsConstructor
public class DictItemBean {

    public static DictItemBean of(String value, String label) {
        final DictItemBean dictItemBean = new DictItemBean();
        dictItemBean.setValue(value);
        dictItemBean.setLabel(label);
        return dictItemBean;
    }

    public static DictItemBean of(SysDictItem item) {
        final DictItemBean dictItemBean = new DictItemBean();
        dictItemBean.setParValue(item.getParValue());
        dictItemBean.setValue(item.getValue());
        dictItemBean.setLabel(item.getCnLabel());
        dictItemBean.setEnLabel(item.getEnLabel());
        dictItemBean.setCnLabel(item.getCnLabel());
        dictItemBean.setOrd(item.getOrd());
        dictItemBean.setValue(item.getValue());
        dictItemBean.setLabel(item.getCnLabel());
        return dictItemBean;
    }

    private String parValue;

    private String value;

    private String label;

    private String enLabel;

    private String cnLabel;

    private Integer ord;

}
