package cn.cpf.web.base.util.exception;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/6/14 15:07
 **/
public class PostException extends RuntimeException {

    private IPostCode postCode;

    public PostException(String message) {
        super(message);
    }

    public PostException(IPostCode postCode) {
        super(postCode.getDesc());
        this.postCode = postCode;
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
    public PostException(IPostCode postCode, Throwable cause) {
        super(cause);
        this.postCode = postCode;
    }

    public IPostCode getPostCode() {
        return postCode;
    }

}
