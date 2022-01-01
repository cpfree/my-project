package cn.cpf.web.base.util.exception;

/**
 * <b>Description : </b> 用于向前台传送消息
 *
 * @author CPF
 * @date 2019/7/26 18:39
 **/
public class PostMessageException extends RuntimeException {

    public PostMessageException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public PostMessageException(String message, Throwable cause) {
        super(message, cause);
    }

}
