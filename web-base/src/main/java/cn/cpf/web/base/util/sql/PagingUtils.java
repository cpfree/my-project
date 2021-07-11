package cn.cpf.web.base.util.sql;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.function.Supplier;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/9/19 10:58
 **/
public class PagingUtils {

    public static <T> PageInfo<T> selectStartPagingInfo(Supplier<List<T>> iSelectList, int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<T> list = iSelectList.get();
        return new PageInfo<>(list);
    }

    public static <T> PageInfo<T> selectOffsetPagingInfo(Supplier<List<T>> iSelectList, int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<T> list = iSelectList.get();
        return new PageInfo<>(list);
    }

}
