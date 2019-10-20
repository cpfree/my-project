package cn.cpf.web.base.util.exception;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/21 16:44
 **/
public class CheckMessageException extends Exception {

    private String desc;

    public CheckMessageException(String message) {
        super(message);
    }

    public CheckMessageException(String message, String desc) {
        super(message);
        this.desc = desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
