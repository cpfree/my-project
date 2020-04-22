package cn.cpf.web.base.util.common;

import cn.cpf.web.base.lang.base.CamelRecord;
import cn.cpf.web.base.lang.base.Record;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class RecordUtil {

    /**
     * 从 list 集合中发现, list 中的对象的 method 无参方法返回的值 等于 obj 的对象
     *
     * @param list   对象集合
     * @param method 方法
     * @param obj    对象
     */
    public static <T> T findBean(List<T> list, Method method, Object obj) {
        Objects.requireNonNull(obj);
        Objects.requireNonNull(method);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        try {
            for (T t : list) {
                Object invoke = method.invoke(t);
                if (invoke.equals(obj)) {
                    return t;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从 list 集合中发现, list 中的对象的 method 无参方法返回的值 等于 obj 的对象
     *
     * @param list 对象集合
     * @param obj  对象
     */
    public static int removeFromCollection(List<Record> list, String key, Object obj) {
        Objects.requireNonNull(obj);
        Objects.requireNonNull(key);
        if (CollectionUtils.isEmpty(list)) {
            return -1;
        }
        List<Record> deletes = null;
        for (Record map : list) {
            if (map.get(key).equals(obj)) {
                if (deletes == null) {
                    deletes = new ArrayList<>(2);
                }
                deletes.add(map);
            }
        }
        if (CollectionUtils.isNotEmpty(deletes)) {
            list.removeAll(deletes);
            return deletes.size();
        }
        return 0;
    }


    public static Record retainAll(Record record1, String... fields) {
        for (String key : fields) {
            if (!record1.containsKey(key)) {
                record1.remove(key);
            }
        }
        return record1;
    }


    public static Record removeAll(Record record1, Record record2) {
        for (String key : record2.keySet()) {
            record1.remove(key);
        }
        return record1;
    }


    public static Record removeAll(Record record1, String... fields) {
        for (String key : fields) {
            record1.remove(key);
        }
        return record1;
    }


    public static Record convert2Record(Map<String, Object> map) {
        Record record = new Record();
        record.putAll(map);
        return record;
    }

    public static CamelRecord convert2CamelRecord(Object object) {
        CamelRecord record = new CamelRecord();
        try {
            for (Field declaredField : object.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                record.set(declaredField.getName(), declaredField.get(object));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return record;
    }

}
