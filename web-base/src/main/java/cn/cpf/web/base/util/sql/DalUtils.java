package cn.cpf.web.base.util.sql;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/29 17:15
 **/
public class DalUtils {

    public static <T> T singleObject(@NonNull List<T> list){
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            String msg = "集合里面发现了多条数据 -> " + list.get(0).getClass().getName();
            throw new RuntimeException(msg);
        }
        return list.get(0);
    }

    public static String likeStrize(String val) {
        if (val == null) {
            return "%%";
        }
        return "%" + val.trim() + "%";
    }

    public static String likeStrizeIfNotNull(String val) {
        if (StringUtils.isBlank(val)) {
            return null;
        }
        return "%" + val.trim() + "%";
    }

}
