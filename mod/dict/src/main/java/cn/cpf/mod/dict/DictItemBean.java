package cn.cpf.mod.dict;

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

    private String value;

    private String label;

}
