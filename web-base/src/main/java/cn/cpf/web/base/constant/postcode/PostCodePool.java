package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/9 15:00
 **/
public class PostCodePool {

    /**
     * // TODO 考虑 hashMap 扩容过程中被多个线程访问是否会出现异常
     * // TODO 考虑是否一下子初始化时就添加所有枚举型
     * 用于存储代码项
     */
    private static final Map<String, IPostCode> CODE_ITEM_MAP = new ConcurrentHashMap<>();

    /**
     * 往 map 中添加代码项
     *
     * @param code code
     * @param postCode postCode 对象
     */
    public static void putPostCode(String code, IPostCode postCode) {
        if (CODE_ITEM_MAP.containsKey(code)) {
            throw new RuntimeException("postCode 中code相同");
        }
        CODE_ITEM_MAP.put(code, postCode);
    }

    static IPostCode getPostCode(String code) {
        return CODE_ITEM_MAP.get(code);
    }

}
