package cn.cpf.web.base.constant.postcode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/9 15:01
 **/
public class PostCodeBean {

    private final String code;

    private final String text;

    private final String desc;

    public PostCodeBean(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
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

}
