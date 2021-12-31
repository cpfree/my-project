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
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * <p>
 * <b>created in </b> 2021/12/29
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@Log4j2
@RequestMapping("/noacc")
@Controller
public class NoAccountController {

    private final IAccUser iAccUser;

    @Autowired
    public NoAccountController(IAccUser iAccUser) {
        this.iAccUser = iAccUser;
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

    /**
     * 注册账号
     * @param request    请求
     * @param userName 用户名 (必填)
     * @param pwd1 密码1
     * @param pwd2 确认密码
     * @param captcha 验证码
     * @param email 必填 (必填)
     */
    @PostMapping("/registerAccount")
    @ResponseBody
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
