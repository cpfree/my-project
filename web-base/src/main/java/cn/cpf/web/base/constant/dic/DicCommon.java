package cn.cpf.web.base.constant.dic;


import cn.cpf.web.base.lang.base.CodeItemPool;
import cn.cpf.web.base.lang.base.ICodeItem;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/12 16:50
 **/
public interface DicCommon {

    enum State implements ICodeItem {
        enable("y", "启用"),
        disable("n", "禁用");

        State(String code, String text) {
            CodeItemPool.putCodeItem(this, code, text);
        }
    }

}
