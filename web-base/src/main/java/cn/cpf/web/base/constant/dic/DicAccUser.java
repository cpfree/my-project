package cn.cpf.web.base.constant.dic;

import com.github.cosycode.codedict.core.IDictItem;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2022/1/1
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
public interface DicAccUser {


    enum LockType implements IDictItem {
        disable("1", "锁定"),
        enable("2", "可用");

        LockType(String value, String label) {
            putItemBean(value, label);
        }
    }

}
