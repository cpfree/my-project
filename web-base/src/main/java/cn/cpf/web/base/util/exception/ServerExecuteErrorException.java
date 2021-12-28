package cn.cpf.web.base.util.exception;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2021/12/28
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
public class ServerExecuteErrorException extends RuntimeException {

    public ServerExecuteErrorException(String message) {
        super(message);
    }

    public ServerExecuteErrorException() {
        super("程序执行失败");
    }

}
