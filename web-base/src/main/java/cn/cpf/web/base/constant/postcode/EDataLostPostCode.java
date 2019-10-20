package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/25 15:55
 **/
public enum EDataLostPostCode implements IPostCode {

    fileDownloadIoException("5006", "文件下载失败", "文件下载IO异常"),
    fileUploadIoException("5007", "文件上传失败", "文件上传IO异常"),
    notFoundUploadFileSource("5008", "未找到上传的文件源", "未发现上传的文件");

    private String code;

    private String text;

    private String desc;

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

    EDataLostPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EDataLostPostCode(String code, String text) {
        this(code, text, text);
    }

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }
}