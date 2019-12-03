package cn.cpf.web.service.mod.sms.template;

import cn.cpf.web.base.util.GeneUtils;

import java.util.Map;

/**
 * <b>Description : </b> 抽象 SMS 短信模板
 *
 * @author CPF
 * @date 2019/7/17 11:02
 **/
public abstract class AbstractSmsTemplate {

    /**
     * 短信类型, 用于区分短信类型来实现不同操作
     */
    public enum TemplateType {

        /**
         * 通知类型短信， 没有验证码
         **/
        notification,
        /**
         * 验证码类型短信， 有验证码
         **/
        verificationCode
    }

    /**
     * 短信验证码
     */
    protected String code;

    AbstractSmsTemplate(String code) {
        this.code = code;
    }

    AbstractSmsTemplate() {
        if (isVerificationCodeTemplate()) {
            this.code = GeneUtils.getRandomCode(6);
        }
    }

    /**
     * @return 模板类型
     */
    public abstract TemplateType getTemplateType();

    /**
     * @return 模板名称
     */
    public abstract String getTemplateName();

    /**
     * 返回模板Code
     */
    public abstract String getTemplateCode();

    /**
     * @return 是否是短信验证模板
     */
    public boolean isVerificationCodeTemplate() {
        return TemplateType.verificationCode.equals(getTemplateType());
    }

    /**
     * 如果有验证码, 则返回验证码
     */
    public String getVerificationCode() {
        return code;
    }

    /**
     * 获取模板参数
     */
    public abstract Map<String, String> getTemplateParams();

}
