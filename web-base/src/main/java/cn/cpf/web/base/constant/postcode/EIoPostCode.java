package cn.cpf.web.base.constant.postcode;

import cn.cpf.web.base.lang.base.IPostCode;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/11 16:17
 **/
public enum EIoPostCode implements IPostCode {

    fileDownloadIoException("5006", "文件下载失败", "文件下载IO异常"),
    fileUploadIoException("5007", "文件上传失败", "文件上传IO异常"),
    notFoundUploadFileSource("5008", "未找到上传的文件源", "未发现上传的文件"),
    notFoundPackageGuid("5009", "文件删除失败", "未发现包裹标识"),
    fileUploadSuccess("0631", "文件上传成功"),
    fileRemoveSuccess("0632", "文件删除成功"),
    fileHasBeenRemoved("0633", "未发现需删除的文件, 请确认文件是否已经删除!"),
    notFoundFileTag("0634", "未发现下载的文件标记， 参数异常!"),
    filePathOrFileNameIsNull("0635", "下载失败！请联系管理员！", "所下载的文件路径或文件名为空");

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

    EIoPostCode(String code, String text, String desc) {
        this.code = code;
        this.text = text;
        this.desc = desc;
    }

    EIoPostCode(String code, String text) {
        this(code, text, text);
    }

    @Override
    public String toString() {
        return "{" + "code='" + code + '\'' + ", text='" + text + '\'' + ", desc='" + desc + '\'' + '}';
    }
}
