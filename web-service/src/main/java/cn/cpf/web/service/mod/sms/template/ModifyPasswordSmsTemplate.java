package cn.cpf.web.service.mod.sms.template;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <b>Description : 短信模版
 * <p>
 * 模板名称 : 修改密码模板
 * 模板类型 : 验证码
 * 模版CODE : SMS_34395438
 * 模版内容 : 验证码${code}，您正在尝试修改${product}登录密码，请妥善保管账户信息。
 *
 * @author CPF
 * @date 2019/6/10 18:06
 **/
public class ModifyPasswordSmsTemplate extends AbstractSmsTemplate {

    private static final String templateCode = "SMS_34395438";

    private static final String templateName = "修改密码模板";

    private static final TemplateType templateType = TemplateType.verificationCode;

    protected String product;

    public ModifyPasswordSmsTemplate(String product, String code) {
        super(code);
        this.product = product;
    }

    public ModifyPasswordSmsTemplate(String product) {
        super();
        this.product = product;
    }

    @Override
    public TemplateType getTemplateType() {
        return templateType;
    }

    @Override
    public String getTemplateName() {
        return templateName;
    }

    @Override
    public String getTemplateCode() {
        return templateCode;
    }

    @Override
    public Map<String, String> getTemplateParams() {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("code", code);
        params.put("product", product);
        return params;
    }

}
