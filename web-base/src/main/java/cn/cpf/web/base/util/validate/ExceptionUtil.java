package cn.cpf.web.base.util.validate;

/**
 * @author CPF
 * @version [版本号, 2018年4月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ExceptionUtil {

    public static final String ILLEGAL_VALUE = "出现了不该出现的值";

    /**
     * 抛出异常标记
     */
    public static final boolean THROW_FLAG = true;

    /**
     * @exception/throws new RuntimeException()
     */
    public static void throwRuntimeException() {
        if (THROW_FLAG) {
            throw new RuntimeException();
        }
    }

    /**
     * @exception/throws new NullPointerException();
     */
    public static void throwNullPointerException() {
        if (THROW_FLAG) {
            throw new NullPointerException();
        }
    }

    /**
     * @param message
     * @exception/throw new RuntimeException(message);
     */
    public static void throwRuntimeException(String message) {
        if (THROW_FLAG) {
            throw new RuntimeException(message);
        }
    }

    /**
     * 出现非法值
     *
     * @exception/throw RuntimeException(ILLEGAL_VALUE);
     */
    public static void throwIllegalValue() {
        throwRuntimeException(ILLEGAL_VALUE);
    }

}
