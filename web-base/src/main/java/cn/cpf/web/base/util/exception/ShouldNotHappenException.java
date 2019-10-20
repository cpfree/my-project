package cn.cpf.web.base.util.exception;

/**
 * <b>Description : </b> 不应该发生的异常
 *
 * @author CPF
 * @date 13:32 2019/3/1
 **/
public class ShouldNotHappenException extends RuntimeException {

    public ShouldNotHappenException(String message) {
        super(message);
    }

    public ShouldNotHappenException() {
        super("this path should not happen!");
    }

}
