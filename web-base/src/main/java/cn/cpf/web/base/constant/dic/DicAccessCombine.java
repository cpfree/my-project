package cn.cpf.web.base.constant.dic;

import cn.cpf.web.base.lang.dict.IDictItem;
import cn.cpf.web.base.lang.dict.StaticDictPool;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 11:42
 **/
public interface DicAccessCombine {

    enum RolePermState implements IDictItem {
        /**
         * 授予权限
         */
        authorize("y", "启用"),
        /**
         * 权限无效状态
         */
        invalid("i", "失效"),
        /**
         * 剥夺权限
         */
        disable("n", "禁用");

        RolePermState(String code, String text) {
            StaticDictPool.putCodeItem(this, code, text);
        }
    }

}
