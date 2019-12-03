package cn.cpf.web.service.mod.sms.template;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <b>Description : 短信模版
 * <p>
 * 模板名称 : 登录确认验证码
 * 模板类型 : 验证码
 * 模版CODE : SMS_34395442
 * 模版内容 : 验证码${code}，您正在登录${product}，若非本人操作，请勿泄露。
 *
 * @author CPF
 * @date 2019/6/10 18:06
 **/
public class LoginConfirmSmsTemplate extends AbstractSmsTemplate {

    private String product;

    public LoginConfirmSmsTemplate(String product) {
        super();
        this.product = product;
    }

    public LoginConfirmSmsTemplate(String product, String code) {
        super(code);
        this.product = product;
    }

    @Override
    public TemplateType getTemplateType() {
        return TemplateType.verificationCode;
    }

    @Override
    public String getTemplateName() {
        return "登录确认验证码";
    }

    @Override
    public String getTemplateCode() {
        return "SMS_34395442";
    }

    @Override
    public Map<String, String> getTemplateParams() {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("code", code);
        params.put("product", product);
        return params;
    }

}
