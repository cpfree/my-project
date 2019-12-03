package cn.cpf.web.service.mod.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * <b>Description : </b> 阿里云短信服务
 *
 * @author CPF
 * @date 2019/11/28 15:47
 **/
@Service
@Slf4j
public class SmsSendService {

    @Value("${sms.accessKey}")
    private String accessKeyId;

    @Value("${sms.secret}")
    private String accessKeySecret;

    @Value("${sms.regionId}")
    private String regionId;

    @Value("${sms.signName}")
    private String signName;


    //初始化ascClient需要的几个参数
    /**
     * 阿里云短信短信API产品名称（短信产品名固定，无需修改）
     */
    private static final String PRODUCT = "Dysmsapi";
    /**
     * 阿里云短信短信API产品域名（接口地址固定，无需修改）
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    private IClientProfile profile;

    @PostConstruct
    public void init() throws ClientException {
        log.debug("init SmsUtil." + regionId + "," + accessKeyId + ",signName" + signName);
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "600");
        //初始化ascClient,暂时不支持多region（请勿修改）
        if (profile == null) {
            profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint(regionId, regionId, PRODUCT, DOMAIN);
        }
    }

    /**
     * 发送短信
     *
     * @param templateCode 模板标识
     * @param phone        接收的手机号码
     * @param params       参数
     * @return
     * @throws ClientException
     */
    public SendSmsResponse sendSms(String templateCode, String phone, Map<String, String> params) throws ClientException {
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        String jsonString = JSONObject.toJSONString(params);
        request.setTemplateParam(jsonString);
        // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        log.debug("短信发送情况 ： {}, message : {}", sendSmsResponse.getCode(), sendSmsResponse.getMessage());
        return sendSmsResponse;
    }


}
