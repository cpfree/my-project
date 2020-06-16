package cn.cpf.web.base.constant.dic;


import com.github.codedict.core.IDictItem;

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
    enum NotesType implements IDictItem {

        SYSTEM("S", "系统通知"),
        BIND("B", "绑定通知"),
        ACTIVE("A", "激活通知"),
        GROUP("G", "小市场通知"),
        FANS("F", "互粉和准入通知"),
        BOND("O", "债券通知");

        NotesType(String value, String label) {
            putItemBean(value, label);
        }
    }

}
