package cn.cpf.web.boot.controller;

import cn.cpf.web.base.constant.postcode.EAccountPostCode;
import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.ELoginPostCode;
import cn.cpf.web.base.lang.base.IPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.base.util.exception.ServerExecuteErrorException;
import cn.cpf.web.boot.util.AccountUtil;
import cn.cpf.web.boot.util.CaptchaUtils;
import cn.cpf.web.boot.util.CpSessionUtils;
import cn.cpf.web.service.base.api.IAccUser;
import cn.cpf.web.service.mod.sms.ISms;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
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
     * 注册账号
     * @param request    请求
     * @param pwd1 密码1
     * @param pwd2 确认密码
     * @param captcha 验证码
     * @param userName 用户名 (必填)
     * @param email 必填 (必填)
     */
    @PostMapping("/registerAccount")
    @ResponseBody
    @Transactional
    public Map<String, Object> registerAccount(HttpServletRequest request, @RequestParam("userName") String userName,
                                               @RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2,
                                               @RequestParam("captcha") String captcha, @RequestParam("email") String email) {
        // check
        // 检查图片验证码
        IPostCode postCode = CaptchaUtils.checkKaptchaCode(request, captcha, userName);
        if (postCode.isNotSuccess()) {
            return PostBean.genePostMap(postCode);
        }
        // 验证两次密码是否相同
        if (pwd1 == null || !pwd1.equals(pwd2)) {
            return PostBean.genePostMap(ELoginPostCode.doublePwdIsNotEquals);
        }
        // 检查"用户名"是否已经存在
        final AccUser accUser = iAccUser.findByUserName(userName);
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
        user.setPhone("");
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
            if (iAccUser.insert(user) != null) {
                log.info("注册成功: {}", user);
            } else {
                // TODO 跳转至500页面
                throw new ServerExecuteErrorException("注册失败");
            }
        } catch (Exception e) {
            log.error("注册失败", e);
            throw new PostException(EAccountPostCode.registerFailure);
        }
        return PostBean.genePostMap(EAccountPostCode.registerSuccess);
    }



    /**
     * 查询相关手机号是否存在
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
//
//    /**
//     * 更新密码
//     */
//    @PostMapping("/resetPassword")
//    @ResponseBody
//    public Map<String, Object> resetPassword(HttpServletRequest request, @RequestParam("phone") String phone, @RequestParam("pwd1") String pwd1, @RequestParam("pwd2") String pwd2, @RequestParam("captcha") String captcha, @RequestParam("verifyCode") String verifyCode) {
//        // check
//        IPostCode postCode = doRegisterBefore(request, phone, pwd1, pwd2, RESET_PWD_SMS_CODE, captcha, verifyCode);
//        if (postCode.isNotSuccess()) {
//            return PostBean.genePostMap(postCode);
//        }
//        // 更改密码
//        // 检查手机号码是否已经存在
//        AccUser user = iAccUser.findByPhone(phone);
//        if (user == null) {
//            return PostBean.genePostMap(ELoginPostCode.ACCOUNT_IS_EXIST);
//        }
//        user.setPassword(pwd1);
//        iAccUser.updateByPrimaryKey(user);
//        return PostBean.genePostMap(ECommonPostCode.SUCCESS);
//    }



    /**
     * 登陆时如果验证不成功, 则返回 json 数据
     */
    @PostMapping("/loginVerification")
    @ResponseBody
    public Map<String, Object> loginVerification(HttpServletRequest request, @RequestParam("userName") String userName, @RequestParam("password") String password,
                                                 @RequestParam("rememberMe") boolean rememberMe) throws InvocationTargetException, IllegalAccessException {
        // TODO 添加映射打印 传入传出参数
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return PostBean.genePostMap(ELoginPostCode.USER_OR_PASSWORD_ERROR);
        }

        PostBean postBean = new PostBean();
        // 获取用户信息
        final AccUser user = iAccUser.findByUserName(userName);
        if (user == null) {
            return PostBean.genePostMap(ELoginPostCode.USER_NOT_FOUND);
        }

        // 如果错误次数大于3, 检查图片验证码
        final int maxErrTime = 3;
        final String needCaptcha = "needCaptcha";
        if (Optional.ofNullable(user.getLoginErrorNum()).orElse(0) >= maxErrTime) {
            final String captcha = request.getParameter("captcha");
            IPostCode postCode = CaptchaUtils.checkKaptchaCode(request, captcha, userName);
            if (postCode.isNotSuccess()) {
                postBean.put(needCaptcha, true);
                postBean.setReturnCode(postCode);
                return postBean.toResultMap();
            }
        }

        // 用户名密码不匹配
        if (!user.getPassword().equals(password)) {
            final int loginErrorNum = Optional.ofNullable(user.getLoginErrorNum()).orElse(0) + 1;
            if (loginErrorNum >= maxErrTime) {
                postBean.put(needCaptcha, true);
            }
            postBean.setReturnCode(ELoginPostCode.USER_OR_PASSWORD_ERROR);
            return postBean.toResultMap();
        }

        // 重置用户查询信息
        user.setLoginErrorNum(0);
        // TODO 程序执行失败相关处理
        if (iAccUser.updateByPrimaryKey(user) <= 0) {
            postBean.setReturnCode(ECommonPostCode.EXEC_FAILURE);
            return postBean.toResultMap();
        }

        // 用户信息添加至 session
        CpSessionUtils.setUser(user);
        return postBean.toResultMap();
    }


}
