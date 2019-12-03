package cn.cpf.web.service.mod.sms.template;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <b>Description : 短信模版
 * <p>
 * 模板名称 : 绑定邮箱账号的短信验证模板
 * 模板类型 : 验证码
 * 模版CODE : SMS_68300060
 * 模版内容 : 您正在绑定邮箱，验证码${code}，有效期${expireTime}分钟，感谢您的支持！
 *
 * @author CPF
 * @date 2019/6/10 18:06
 **/
public class BindMailSmsTemplate extends AbstractSmsTemplate {

    private int expireTime;

    public BindMailSmsTemplate() {
        super();
        this.expireTime = 10;
    }

    /**
     * code 默认6位数字
     *
     * @param expireTime 分钟
     */
    public BindMailSmsTemplate(int expireTime) {
        super();
        this.expireTime = expireTime;
    }

    public BindMailSmsTemplate(String code, int expireTime) {
        super(code);
        this.expireTime = expireTime;
    }

    @Override
    public TemplateType getTemplateType() {
        return TemplateType.verificationCode;
    }

    @Override
    public String getTemplateName() {
        return "绑定邮箱验证码";
    }

    @Override
    public String getTemplateCode() {
        return "SMS_68300060";
    }

    @Override
    public Map<String, String> getTemplateParams() {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("code", code);
        params.put("expireTime", String.valueOf(expireTime));
        return params;
    }



}
