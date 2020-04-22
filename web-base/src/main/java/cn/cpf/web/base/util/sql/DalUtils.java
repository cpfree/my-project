package cn.cpf.web.base.util.sql;

import cn.cpf.web.base.constant.postcode.ECommonWarningCode;
import cn.cpf.web.base.util.other.LocalThreadHolder;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/29 17:15
 **/
public interface DalUtils {

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

    static <T> T getAncCheckOne(Collection<T> collections){
        if (CollectionUtils.isEmpty(collections)) {
            return null;
        }
        if (collections.size() > 1) {
            LocalThreadHolder.putWarning(ECommonWarningCode.atMostOneButFoundMore);
        }
        return collections.iterator().next();
    }

}
