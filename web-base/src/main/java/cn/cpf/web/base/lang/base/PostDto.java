package cn.cpf.web.base.lang.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/9 10:41
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto<T> implements Serializable {

    private transient IPostCode returnCode = null;

    private T bean = null;

    private String code;

    private String text;

    private String desc;

    public boolean isSuccess() {
        return IPostCode.isSuccess(code);
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getDesc() {
        return desc;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setReturnCode(IPostCode returnCode) {
        this.returnCode = returnCode;
        this.code = returnCode.getCode();
        this.text = returnCode.getText();
        this.desc = returnCode.getDesc();
    }

    public IPostCode getReturnCode() {
        return returnCode;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "PostDto{" + "bean=" + bean + ", code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }

}
