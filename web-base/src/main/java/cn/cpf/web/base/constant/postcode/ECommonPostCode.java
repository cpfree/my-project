package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/3/20 15:32
 **/
public enum ECommonPostCode implements IPostCode {


    SUCCESS("0000", "操作成功"),
    NO_EXCEPTION("0002", "没有出现异常!"),
    INTERNAL_ERROR("1001", "Internal error."),
    INTERNAL_RUNTIME_ERROR("1002", "Internal runtime error."),
    SYSTEM_CONFIG_EXCEPTION("1003", "system config exception."),
    SESSION_INVALID("1004", "会话超时"),
    dataLost("1005", "数据丢失"),
    HasNoPermissionForThisOperation("1006", "您并不具有该操作的权限"),
    ILLEGAL_ARGUMENT_EXCEPTION("1007", "参数检查异常!"),
    shouldNotHappened("1008", "Internal error.", "没有考虑到的情况出现"),
    EXEC_FAILURE("1009", "执行失败."),
    PostMessageException("1099", "PostMessageException!");

    private String code;

    private String text;

    private String desc;

    ECommonPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    ECommonPostCode(String code, String text) {
        this(code, text, text);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }
}
