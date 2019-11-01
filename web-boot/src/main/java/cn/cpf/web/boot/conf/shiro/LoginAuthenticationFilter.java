package cn.cpf.web.boot.conf.shiro;

import cn.cpf.web.base.constant.dic.DicCommon;
import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.ELoginPostCode;
import cn.cpf.web.base.lang.base.IPostCode;
import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.boot.conf.GlobalConfig;
import cn.cpf.web.boot.util.CpSessionUtils;
import cn.cpf.web.service.base.api.IAccUser;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/29 23:44
 **/
@Slf4j
public class LoginAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private IAccUser iAccUser;

    /**
     * 一般被 preHandler(在拦截器链执行之前执行) 调用
     *
     * 请求首先进入此方法, 判断当前请求是否已认证, 如果没有认证
     *
     * @return 如果返回true则继续拦截器链；否则中断后续的拦截器链的执行直接返回；进行预处理（如基于表单的身份验证、授权）
     */
    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // 判断当前请求是否已被认证 --> 1. 判断请求权限是否已验证 或者 当前请求不是登录请求, 2. 检查参数是否合法
        boolean isAllowed = isAccessAllowed(request, response, mappedValue);
        // 如果当前请求被允许, 同时是登录请求, 则直接跳转至登录成功页面
        if (isAllowed && isLoginRequest(request, response)) {
            issueSuccessRedirect(request, response);
            return false;
        }
        // 如果 isAllowed 为 true 则代表已认证, 若为 false 则执行没有认证时的处理方法 onAccessDenied
        return isAllowed || onAccessDenied(request, response, mappedValue);
    }


    /**
     * 请求未认证时的处理方法, 一般情况下, 请求未认证跳转至登录页面的逻辑就只在此处执行.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 注意此方法为父类方法, 并没有改动
        // 首先判断当前请求是否是登录请求 --> 默认判断请求的Url是否是登录的URL
        if (this.isLoginRequest(request, response)) {
            // 相当于是对请求方法进行限制 --> 默认判断当前方法是否是 HTTP 请求, 以及判断请求方法是否是POST方法
            if (this.isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                // 执行登录方法
                return this.executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            // 如果不是登录请求的话, 打印日志, 同时重定向至登录页面
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
            }
            // 存储请求, 并跳转到登录页面, 待登录成功后再重定向至请求页面
            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    /**
     * 执行登录方法
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        // 获取登录请求的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) createToken(request, response);
        if (token == null) {
            String msg = "请求用户名密码获取错误";
            throw new IllegalStateException(msg);
        }
        Subject subject = this.getSubject(request, response);
        try {
            // 账户密码验证
            subject.login(token);
            // 进行额外的验证
            final IPostCode iPostCode = extraCheck(request);
            // 如果额外验证不成功, 则抛出异常
            if (iPostCode.isNotSuccess()) {
                // 向前台返回数据
                request.setAttribute("postCode", iPostCode);
                throw new AuthenticationException(iPostCode.toJsonString());
            }
        } catch (AuthenticationException e) {
            return this.onLoginFailure(token, e, request, response);
        }
        return this.onLoginSuccess(token, subject, request, response);
    }

    /**
     * 登录失败执行逻辑
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        // 1. 记录登录错误次数 + 1
        final AccUser user = CpSessionUtils.getUser();
        user.setLoginErrorNum(Optional.of(user.getLoginErrorNum()).orElse(0) + 1);
        iAccUser.updateByPrimaryKey(user);
        // 2. 清除 session 等
        // 3. 如果原先已登录, 则执行退出登录方法
        SecurityUtils.getSubject().logout();
        return super.onLoginFailure(token, e, request, response);
    }

    /**
     * 登录成功执行逻辑
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
        try {
            // 1. 重置登录次数
            final AccUser user = CpSessionUtils.getUser();
            user.setLoginErrorNum(0);
            iAccUser.updateByPrimaryKey(user);
            // 初始化 session 等
            return super.onLoginSuccess(token, subject, request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return onLoginFailure(token, new AuthenticationException("登录成功后相应失败", e), request, response);
        }
    }

    /**
     * 额外进行验证
     */
    private IPostCode extraCheck(ServletRequest request) {
        // 获取用户对象, 用户对象在subject.login()调用 doGetAuthenticationInfo() 方法时被存在session里
        @NonNull final AccUser user = CpSessionUtils.getUser();
        // 1. 如果错误次数过多, 则需要验证码
        if (Optional.ofNullable(user.getLoginErrorNum()).orElse(0) >= GlobalConfig.getMaxLoginWithNoCaptchaCode()) {
            String checkCode = request.getParameter("captcha");
            final IPostCode iPostCode = checkKaptchaCode((HttpServletRequest) request, checkCode, user);
            if (iPostCode.isNotSuccess()) {
                return iPostCode;
            }
        }
        // 2. 检查用户状态
        // 当前账户已禁用
        if (DicCommon.State.disable.isCode(user.getState())) {
            return ELoginPostCode.ACCOUNT_IS_DISABLED;
        }
        return ECommonPostCode.NO_EXCEPTION;
    }


    /**
     * 检查 Kaptcha 验证码
     */
    public static IPostCode checkKaptchaCode(HttpServletRequest request, String checkCode, AccUser user) {
        String kaptchaValue = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isBlank(kaptchaValue)) {
            log.warn(String.format("User {%s} kaptcha code is invalid.", user.getName()));
            return ELoginPostCode.VERIFICATION_CODE_INVALIDATION;
        } else if (StringUtils.isBlank(checkCode)) {
            log.warn(String.format("User {%s} code is blank.", user.getName()));
            return ELoginPostCode.VERIFICATION_CODE_NOT_FOUND;
        } else if (!kaptchaValue.equalsIgnoreCase(checkCode)) {
            log.warn(String.format("User {%s} code doesn't match.", user.getName()));
            return ELoginPostCode.VERIFICATION_CODE_ERROR;
        }
        return ECommonPostCode.NO_EXCEPTION;
    }

}
