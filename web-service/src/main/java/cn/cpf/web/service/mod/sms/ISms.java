package cn.cpf.web.service.mod.sms;

import cn.cpf.web.base.constant.postcode.ESmsPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.service.mod.sms.template.AbstractSmsTemplate;

import java.util.Map;

/**
 * <b>Description : </b> 手机短信服务接口
 *
 * @author CPF
 * @date 2019/3/14 14:47
 **/
public interface ISms {

    /**
     * 发送短信验证模板
     *
     * @param verifyKey   验证标记, 区分各种不同的短信
     * @param smsTemplate 模板类
     * @param phone       手机号
     */
    PostBean sendSmsVerificationCode(String verifyKey, AbstractSmsTemplate smsTemplate, String phone);

    /**
     * 发送短信验证模板
     *
     * @param templateCode 短信模板编号
     * @param phone        手机号码
     * @param map          模板字段
     */
    PostBean sendSmsVerificationCode(String verifyKey, String templateCode, String phone, Map<String, String> map);

    /**
     * 检查短信验证码是否匹配
     *
     * @param verifyKey 验证标记
     * @param phone     手机号码
     * @param code      验证码
     */
    ESmsPostCode verifySmsCode(String verifyKey, String phone, String code);


}
