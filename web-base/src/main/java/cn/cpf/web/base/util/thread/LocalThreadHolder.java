package cn.cpf.web.base.util.thread;

import cn.cpf.web.base.lang.base.IWarningCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/25 11:41
 **/
public class LocalThreadHolder {

    private static ThreadLocal<IWarningCode> codeThreadLocal = new ThreadLocal<>();

    public static void putWarning(IWarningCode warningCode) {
        codeThreadLocal.set(warningCode);
    }

    public static IWarningCode getWarning() {
        return codeThreadLocal.get();
    }

}
