package cn.cpf.web.base.constant.dic;

import cn.cpf.web.base.lang.base.CodeItemPool;
import cn.cpf.web.base.lang.base.ICodeItem;

/**
 * <b>Description : </b> 通知表
 *
 * @author CPF
 * @date 2019/3/4 15:50
 **/
public interface DicItyNotes {

    /**
     * <b>Description : </b> 通知类型
     *
     * @author CPF
     * @date 2019/10/20 15:50
     **/
    enum NotesType implements ICodeItem {

        SYSTEM("S", "系统通知"), BIND("B", "绑定通知"), ACTIVE("A", "激活通知"), GROUP("G", "小市场通知"), FANS("F", "互粉和准入通知"), BOND("O", "债券通知");

        NotesType(String code, String text) {
            CodeItemPool.putCodeItem(this, code, text);
        }
    }

}
