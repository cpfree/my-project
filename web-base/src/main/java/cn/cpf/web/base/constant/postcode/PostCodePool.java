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
    private static final Map<String, IPostCode> codeItemMap = new ConcurrentHashMap<>();

    /**
     * 往 map 中添加代码项
     *
     * @param code
     * @param postCode
     */
    public static final void putPostCode(String code, IPostCode postCode) {
        if (codeItemMap.containsKey(code)) {
            throw new RuntimeException("postCode 中code相同");
        }
        codeItemMap.put(code, postCode);
    }

    static final IPostCode getPostCode(String code) {
        return codeItemMap.get(code);
    }

}
