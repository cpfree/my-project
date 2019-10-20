package cn.cpf.web.base.lang.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <b>Description : </b> 字典表接口
 * @author CPF
 * @date 2019/2/26 14:17
 **/
public class CodeItemPool {

    private CodeItemPool() {
    }

    /**
     * // TODO 考虑 hashMap 扩容过程中被多个线程访问是否会出现异常
     * // TODO 考虑是否一下子初始化时就添加所有枚举型
     * 用于存储代码项
     */
    private static final Map<ICodeItem, CodeItemBean> codeItemMap = new ConcurrentHashMap<>();

    /**
     * 往 map 中添加代码项
     */
    public static void putCodeItem(ICodeItem iCodeItem, String code, String text) {
        codeItemMap.put(iCodeItem, new CodeItemBean(code, text));
    }

    static CodeItemBean getCodeItem(ICodeItem iCodeItem) {
        return codeItemMap.get(iCodeItem);
    }

}
