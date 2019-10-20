package cn.cpf.web.base.util.common;

import cn.cpf.web.base.lang.base.Record;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author CPF
 * @version [版本号, 2017年12月26日]
 */
public class CompareUtil {

    public static final String SAME_FLAG = "sameflag";

    public static void compareList(List<Record> list1, List<Record> list2, String flagfield, String[] comparefields) {
        // 判断传入的参数是否符合条件, 若不符合则返回
        if (list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty() || StringUtils.isBlank(flagfield) || comparefields == null) {
            return;
        }

        Iterator<Record> iterator = list1.iterator();
        while (iterator.hasNext()) {
            Record record1 = iterator.next();
            String flagvalue = record1.getString(flagfield);
            Record record2 = findCounterpart(list2, flagfield, flagvalue);
            if (record2 != null) {
                if (compareRecord(record1, record2, comparefields)) {
                    record1.put(SAME_FLAG, "1");
                    record2.put(SAME_FLAG, "1");
                }
            }
        }
    }

    public static void compareListNullFill(List<Record> list1, List<Record> list2, String flagfield, String[] comparefields) {
        // 判断传入的参数是否符合条件, 若不符合则返回
        if (list1 == null || list1.isEmpty() || list2 == null || list2.isEmpty() || StringUtils.isBlank(flagfield) || comparefields == null) {
            return;
        }
        List<Record> _list2 = new ArrayList<>();
        Iterator<Record> iterator = list1.iterator();
        while (iterator.hasNext()) {
            Record record1 = iterator.next();
            String flagvalue = record1.getString(flagfield);
            Record record2 = findCounterpart(list2, flagfield, flagvalue);
            if (record2 != null) {
                if (compareRecord(record1, record2, comparefields)) {
                    record1.put(SAME_FLAG, "1");
                    record2.put(SAME_FLAG, "1");
                }
                _list2.add(record2);
                list2.remove(record2);
            } else {
                _list2.add(new Record());
            }
        }

        for (Record item : list2) {
            list1.add(new Record());
            _list2.add(item);
        }
        list2.clear();
        list2.addAll(_list2);
    }

    /**
     * 在实体列表中通过 标记字段flagfield 以及其值flagvalue 来查找对象
     *
     * @param list
     * @param flagfield
     * @param flagvalue
     * @return 查到的实体对象, 若没查到则返回 null
     */
    public static <T extends Record> T findCounterpart(List<T> list, String flagfield, String flagvalue) {
        if (StringUtils.isNotBlank(flagfield) && StringUtils.isNotBlank(flagvalue) && list != null) {
            for (T item : list) {
                if (flagvalue.equals(item.getString(flagfield))) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * 比较两个实体对象的某些字段是否相等
     *
     * @param record1
     * @param record2
     * @param comparefields 比较的字段
     * @return 相同返回true, 若有一个不相同则返回false
     */
    public static boolean compareRecord(Record record1, Record record2, String[] comparefields) {
        for (String field : comparefields) {
            if (!isEqual(record1.get(field), record2.get(field))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个对象是否相等
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean isEqual(Object obj1, Object obj2) {
        if (obj1 == null) {
            return obj2 == null;
        } else {
            return obj1.equals(obj2);
        }
    }

}
