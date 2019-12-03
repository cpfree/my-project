package cn.cpf.web.service.mod.sms;

import cn.cpf.web.base.constant.postcode.ESmsPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.util.GeneUtils;
import cn.cpf.web.service.mod.sms.template.AbstractSmsTemplate;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <b>Description : </b> 手机短信服务实现类
 *
 * @author CPF
 * @date 2019/3/14 15:16
 **/
@Service
public class SmsImpl implements ISms {

    @Autowired
    private SmsSendService smsSendService;

    @Value("${sms.needSms}")
    private String needSms;

    @Value("${sms.expireTime}")
    private long expireTime;

    @Value("${sms.checkCodeLength}")
    private int checkCodeLength;

    /**
     * 存储已发送的手机验证码
     */
    private Cache<String, String> validateCodeCache;

    private static final Logger logger = LoggerFactory.getLogger(ISms.class);

    @PostConstruct
    public void init() {
        validateCodeCache = CacheBuilder.newBuilder().expireAfterWrite(expireTime, TimeUnit.SECONDS).build();
    }



    @Override
    public PostBean sendSmsVerificationCode(String verifyKey, AbstractSmsTemplate smsTemplate, String phone) {
        // check
        if (smsTemplate == null || StringUtils.isAnyBlank(verifyKey)) {
            return new PostBean(ESmsPostCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }
        // 判断 SMS 服务是否启动
        if (!"Y".equalsIgnoreCase(needSms)) {
            return new PostBean(ESmsPostCode.SMS_IS_CLOSE);
        }
        // 判断手机号码是否为空
        if (StringUtils.isBlank(phone)) {
            return new PostBean(ESmsPostCode.PHONE_NUMBER_IS_BLANK);
        }
        logger.debug("调用了发送短信的服务, 发送了 {} 的短信模板; phone : {}", smsTemplate.getTemplateName(), phone);
        // 生成 checkCodeLength 位验证码
        try {
            SendSmsResponse smsResponse = smsSendService.sendSms(smsTemplate.getTemplateCode(), phone, smsTemplate.getTemplateParams());
            PostBean postBean = new PostBean();
            if ("OK".equals(smsResponse.getCode())) {
                postBean.setReturnCode(ESmsPostCode.SUCCESS);
                // 如果是验证码类的短信, 则添加验证码
                if (smsTemplate.isVerificationCodeTemplate()) {
                    validateCodeCache.put(verifyKey + phone, smsTemplate.getVerificationCode());
                }
            } else {
                postBean.setReturnCode(ESmsPostCode.ALI_YUN_ERROR_RECEIPT);
            }
            postBean.put("smsCode", smsResponse.getCode());
            postBean.put("smsMsg", smsResponse.getMessage());
            return postBean;
        } catch (ClientException e) {
            logger.error("sendForgetSmsCode error: ", e);
            return new PostBean(ESmsPostCode.CLIENT_EXCEPTION);
        }
    }


    @Override
    public PostBean sendSmsVerificationCode(String verifyKey, String templateCode, String phone, Map<String, String> map) {
        // 判断 SMS 服务是否启动
        if (!"Y".equalsIgnoreCase(needSms)) {
            logger.debug("SMS 短信服务未启动");
            return new PostBean(ESmsPostCode.SMS_IS_CLOSE);
        }
        logger.debug("调用了发送短信的服务, phone : {}, templateCode : {}, map : {}", phone, templateCode, map);
        // 生成 checkCodeLength 位验证码
        String code = GeneUtils.getRandomCode(checkCodeLength);
        return doSendSmsWork(verifyKey, templateCode, phone, map, code);
    }


    @Override
    public ESmsPostCode verifySmsCode(String verifyKey, String phone, String code) {
        // 判断 SMS 服务是否启动
        if (!"Y".equalsIgnoreCase(needSms)) {
            return ESmsPostCode.SMS_IS_CLOSE;
        }
        verifyKey = verifyKey + phone;
        String cachedCode = validateCodeCache.getIfPresent(verifyKey);
        if (cachedCode == null) {
            // 验证码已过期返回false
            return ESmsPostCode.VERIFICATION_CODE_EXPIRED;
        }
        boolean equals = cachedCode.equals(code);
        if (equals) {
            // 验证成功之后使缓存失效
            validateCodeCache.invalidate(verifyKey);
            return ESmsPostCode.VERIFICATION_SUCCESS;
        }
        return ESmsPostCode.VERIFICATION_FAILURE;
    }


    /**
     * 发送短信调用方法
     *
     * @param templateCode 模板
     * @param phone        手机号码
     * @param map          字段集合
     * @param code         需要存入缓存的code
     */
    private PostBean doSendSmsWork(String verifyKey, String templateCode, String phone, Map<String, String> map, String code) {
        if (StringUtils.isAnyBlank(verifyKey, templateCode, phone)) {
            return new PostBean(ESmsPostCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }
        try {
            SendSmsResponse smsResponse = smsSendService.sendSms(templateCode, phone, map);
            PostBean postBean = new PostBean();
            if ("OK".equals(smsResponse.getCode())) {
                postBean.setReturnCode(ESmsPostCode.SUCCESS);
                validateCodeCache.put(verifyKey + phone, code);
            } else {
                postBean.setReturnCode(ESmsPostCode.ALI_YUN_ERROR_RECEIPT);
            }
            postBean.put("smsCode", smsResponse.getCode());
            postBean.put("smsMsg", smsResponse.getMessage());
            return postBean;
        } catch (ClientException e) {
            logger.error("sendForgetSmsCode error: ", e);
            return new PostBean(ESmsPostCode.CLIENT_EXCEPTION);
        }
    }

}
