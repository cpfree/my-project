package cn.cpf.web.base.constant.dic;


import cn.cpf.web.base.lang.dict.IDictItem;
import cn.cpf.web.base.lang.dict.StaticDictPool;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/12 16:50
 **/
public interface DicCommon {

    enum State implements IDictItem {
        enable("y", "启用"),
        disable("n", "禁用");

        State(String code, String text) {
            StaticDictPool.putCodeItem(this, code, text);
        }
    }

}
