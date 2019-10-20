package cn.cpf.web.base.lang.base;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/7/31 10:45
 **/
public class PostObject<T> implements IPostCode {

    private T object = null;

    private IPostCode postCode = null;

    public PostObject() {
    }

    public PostObject(IPostCode postCode) {
        this.postCode = postCode;
    }

    public PostObject(T object) {
        this.object = object;
    }

    public PostObject(T object, IPostCode postCode) {
        this.object = object;
        this.postCode = postCode;
    }

    public IPostCode getPostCode() {
        return postCode;
    }

    public void setPostCode(IPostCode postCode) {
        this.postCode = postCode;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    /**
     * 如果 postCode 为空, 则设置值
     */
    public void setPostCodeIfNull(IPostCode postCode) {
        if (this.postCode == null) {
            this.postCode = postCode;
        }
    }

    @Override
    public String getCode() {
        return postCode.getCode();
    }

    @Override
    public String getText() {
        return postCode.getText();
    }

    @Override
    public String getDesc() {
        return postCode.getDesc();
    }

}
