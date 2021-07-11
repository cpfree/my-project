package cn.cpf.web.boot.controller;

import cn.cpf.web.base.constant.postcode.EAccountPostCode;
import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.ELoginPostCode;
import cn.cpf.web.base.constant.postcode.ESmsPostCode;
import cn.cpf.web.base.lang.base.IPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.boot.util.AccountUtil;
import cn.cpf.web.boot.util.CaptchaUtils;
import cn.cpf.web.service.base.api.IAccUser;
import cn.cpf.web.service.mod.sms.ISms;
import cn.cpf.web.service.mod.sms.template.LoginConfirmSmsTemplate;
import cn.cpf.web.service.mod.sms.template.ModifyPasswordSmsTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/11/27 16:38
 **/
@Controller
@Log4j2
@RequestMapping("/account")
public class AccountController {

    private final IAccUser iAccUser;
    private final ISms iSms;

    @Autowired
    public AccountController(IAccUser iAccUser, ISms iSms) {
        this.iAccUser = iAccUser;
        this.iSms = iSms;
    }

    /**
     * 登陆时如果验证不成功, 则返回 json 数据
     */
    @PostMapping("/registerAccount")
    @ResponseBody
    @Transactional
    public Map<String, Object> registerAccount(HttpServletRequest request, @RequestParam("phone") String phone, @RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2, @RequestParam("captcha") String captcha, @RequestParam("verifyCode") String verifyCode, @RequestParam("userName") String userName, @RequestParam("email") String email) {
        // check
        IPostCode postCode = doRegisterBefore(request, phone, pwd1, pwd2, REGISTER_SMS_CODE, captcha, verifyCode);
        if (postCode.isNotSuccess()) {
            return PostBean.genePostMap(postCode);
        }
        // 检查手机号码是否已经存在
        final AccUser accUser = iAccUser.findByPhone(phone);
        if (accUser != null) {
            return PostBean.genePostMap(ELoginPostCode.ACCOUNT_IS_EXIST);
        }
        // 执行注册
        Date now = new Date();
        String userGuid = UUID.randomUUID().toString();
        AccUser user = new AccUser();
        user.setGuid(userGuid);
        user.setName(userName);
        user.setNickname("");
        user.setPhone(phone);
        user.setEmail(email);
        user.setQqNo("");
        user.setAvatar("");
        user.setAvatarPath("");
        user.setGender("");
        user.setBirthday(null);
        user.setJob("");
        user.setProvince("");
        user.setCity("");
        user.setAddress("");
        user.setPassword(AccountUtil.passwordEncode(pwd1));
        user.setLoginErrorNum(0);
        user.setLockType("");
        user.setAddTime(now);
        user.setUpdateTime(now);
        user.setRemark("");
        user.setState("");

        try {
            AccUser insert = iAccUser.insert(user);
        } catch (Exception e) {
            log.error("注册失败", e);
            throw new PostException(EAccountPostCode.registerFailure);
        }

        return PostBean.genePostMap(EAccountPostCode.registerSuccess);
    }

    /**
     * 查询相关手机号是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping("/isExistPhone")
    @ResponseBody
    public Map<String, Object> isExistPhone(HttpServletRequest request, @RequestParam("phone") String phone) {
        AccUser user = iAccUser.findByPhone(phone);
        if (user != null) {
            return PostBean.genePostMap(ELoginPostCode.ACCOUNT_IS_EXIST);
        }
        return PostBean.genePostMap(ECommonPostCode.NO_EXCEPTION);
    }

    /**
     * 登陆时如果验证不成功, 则返回 json 数据
     */
    @PostMapping("/resetPassword")
    @ResponseBody
    public Map<String, Object> resetPassword(HttpServletRequest request, @RequestParam("phone") String phone, @RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2, @RequestParam("captcha") String captcha, @RequestParam("verifyCode") String verifyCode) {
        // check
        IPostCode postCode = doRegisterBefore(request, phone, pwd1, pwd2, RESET_PWD_SMS_CODE, captcha, verifyCode);
        if (postCode.isNotSuccess()) {
            return PostBean.genePostMap(postCode);
        }
        // 更改密码
        // 检查手机号码是否已经存在
        AccUser user = iAccUser.findByPhone(phone);
        if (user == null) {
            return PostBean.genePostMap(ELoginPostCode.ACCOUNT_IS_EXIST);
        }
        user.setPassword(pwd1);
        iAccUser.updateByPrimaryKey(user);
        return PostBean.genePostMap(ECommonPostCode.SUCCESS);
    }

    /**
     * 检查图片验证码, 短信验证码, 两次密码是否相同
     */
    private IPostCode doRegisterBefore(HttpServletRequest request, String phone, String pwd1, String pwd2, String smsVerifyKey, String captcha, String verifyCode) {
        // 检查图片验证码
        IPostCode postCode = CaptchaUtils.checkKaptchaCode(request, captcha, phone);
        if (postCode.isNotSuccess()) {
            return postCode;
        }
        // 验证短信验证码
        ESmsPostCode smsPostCode = iSms.verifySmsCode(smsVerifyKey, phone, verifyCode);
        if (smsPostCode.isNotSuccess()) {
            return smsPostCode;
        }
        // 验证两次密码是否相同
        if (pwd1 == null || !pwd1.equals(pwd2)) {
            return ELoginPostCode.doublePwdIsNotEquals;
        }
        return ECommonPostCode.NO_EXCEPTION;
    }

    /**
     * 注册验证码
     */
    private static final String REGISTER_SMS_CODE = "sendRegisterSmsCode";
    /**
     * 重置密码验证码
     */
    private static final String RESET_PWD_SMS_CODE = "sendResetPwdSmsCode";

    /**
     * 首先验证图片验证码, 通过后, 向指定手机号发送短信验证码
     *
     * @param captcha 用户输入的验证码
     * @param phone 电话号码
     */
    @PostMapping("/sendResetPwdSmsCode")
    @ResponseBody
    public Map<String, Object> sendOpenHallResetPasswordSmsCode(HttpServletRequest request, @RequestParam("captcha") String captcha, @RequestParam("phone") String phone) {
        // 检查图片验证码
        IPostCode postCode = CaptchaUtils.checkKaptchaCode(request, captcha, phone);
        if (postCode.isNotSuccess()) {
            return PostBean.genePostMap(postCode);
        }
        PostBean postBean = iSms.sendSmsVerificationCode(RESET_PWD_SMS_CODE, new ModifyPasswordSmsTemplate("i同业"), phone);
        return postBean.toResultMap();
    }

    /**
     * 首先验证图片验证码, 通过后, 向指定手机号发送短信验证码
     */
    @PostMapping("/sendRegisterSmsCode")
    @ResponseBody
    public Map<String, Object> sendRegisterSmsCode(HttpServletRequest request, @RequestParam("captcha") String captcha, @RequestParam("phone") String phone) {
        // 检查图片验证码
        IPostCode postCode = CaptchaUtils.checkKaptchaCode(request, captcha, phone);
        if (postCode.isNotSuccess()) {
            return PostBean.genePostMap(postCode);
        }
        PostBean postBean = iSms.sendSmsVerificationCode(REGISTER_SMS_CODE, new LoginConfirmSmsTemplate("i同业"), phone);
        return postBean.toResultMap();
    }

}
