package cn.cpf.web.service.mod.sms.template;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <b>Description : 短信模版, 与阿里云上面的配置对应
 * <p>
 * 模板名称 : 注册账号的短信验证模板
 * 模板类型 : 验证码
 * 模版CODE : SMS_51690036
 * 模版内容 : 您正在注册成为${product}用户，验证码${code}，有效期${expireTime}，感谢您的支持！
 *
 * @author CPF
 * @date 2019/6/10 18:06
 **/
public class RegisterSmsTemplate extends AbstractSmsTemplate {

    private String product;

    private String expireTime;

    /**
     * @param product 产品名称
     */
    public RegisterSmsTemplate(String product) {
        super();
        this.product = product;
        this.expireTime = "10分钟";
    }

    @Override
    public TemplateType getTemplateType() {
        return TemplateType.verificationCode;
    }

    @Override
    public String getTemplateName() {
        return "账号注册验证码";
    }

    @Override
    public String getTemplateCode() {
        return "SMS_51690036";
    }

    @Override
    public Map<String, String> getTemplateParams() {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(3);
        params.put("code", code);
        params.put("product", product);
        params.put("expireTime", expireTime);
        return params;
    }

}
