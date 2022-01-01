package cn.cpf.web.boot.conf.shiro;

import cn.cpf.web.base.constant.dic.DicAccUser;
import cn.cpf.web.base.constant.dic.DicCommon;
import cn.cpf.web.base.constant.postcode.ELoginPostCode;
import cn.cpf.web.base.lang.base.IPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.base.util.cast.JsonUtils;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.boot.conf.GlobalConfig;
import cn.cpf.web.boot.constant.PostKeyConst;
import cn.cpf.web.boot.util.CaptchaUtils;
import cn.cpf.web.boot.util.CpRequestUtils;
import cn.cpf.web.boot.util.CpSessionUtils;
import cn.cpf.web.boot.util.ScResponseUtils;
import cn.cpf.web.service.base.api.IAccUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2019/10/29 23:44
 * </p>
 *
 * @author CPF
 **/
@Slf4j
@Component("scLogin")
public class ScLoginFilter extends FormAuthenticationFilter {

    @Autowired
    private IAccUser iAccUser;

    /**
     * 一般被 preHandler(在拦截器链执行之前执行) 调用
     * <p>
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
                // 到此, 应是访问的 `/login`, 应该是 POST 方法, 是已经打开了 `/login` 页面, 执行了登录请求.
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                final PostBean postBean = new PostBean();
                try {
                    // 执行登录方法
                    return this.executeLogin(request, response);
                } catch (PostException e) {
                    postBean.setReturnCode(e.getPostCode());
                    // 若是 ajax 请求, 则直接返回 json, 不再走过滤器
                    postBean.put(PostKeyConst.NEED_CAPTCHA, true);
                } catch (AuthenticationException e) {
                    postBean.setReturnCode(ELoginPostCode.LOGIN_ERROR);
                    // 若是 ajax 请求, 则直接返回 json, 不再走过滤器
                    postBean.put(PostKeyConst.NEED_CAPTCHA, true);
                    postBean.put("msg", e.getMessage());
                } finally {
                    final Map<String, Object> objectMap = postBean.toResultMap();
                    ScResponseUtils.writeApplicationJson(response, JsonUtils.toJson(objectMap));
                }
                // 因为 shiro 默认登录成功之后, 将页面重定向至 index 页面, 而若请求是 ajax 的话, 则前台无法进行重定向,
                // 因此这里返回 false, 不再走接下来的 Filter 和 Controller, 而是直接返回 json 数据给前台.
                return false;
            } else {
                // 到此, 应是访问的 `/login`, 但只是 GET 方法, 应该是在请求打开 login 页面
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
     * <p>
     * // TODO 防止同一IP, 同一电脑, 多次执行登录请求
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws IOException {
        // 获取登录请求的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) createToken(request, response);
        if (token == null) {
            throw new PostException(ELoginPostCode.LOGIN_REQUEST_ERROR);
        }
        log.info("创建 UsernamePasswordToken {} : " + token, token.hashCode());
        /*
         *  有3种情况, 1. 初次登录并不需要 captcha, 2. 同一IP尝试多次登录, 导致需要 captcha, 3. 用户登录失败多次导致需要 captcha
         */
        IPostCode captchaCheck = CaptchaUtils.checkKaptchaCode((HttpServletRequest) request, token.getUsername());
        // 若 请求数据 和 session 里面均有验证码, 但是不匹配, 则直接返回错误
        if (captchaCheck == ELoginPostCode.VERIFICATION_CODE_NOT_MATCH) {
            throw new PostException(captchaCheck);
        }

        Subject subject = this.getSubject(request, response);
        try {
            // 账户密码验证, 自定义的 realm 通过 token 获取数据库中的用户数据, 并使用 realm 中的匹配器匹配密码
            subject.login(token);

            /* 到此, 则证明 用户密码匹配成功, 接下来进行额外的验证 */
            // 用户对象在subject.login()调用 doGetAuthenticationInfo() 方法时被存在session里
            final AccUser user = CpSessionUtils.getUser();
            // 1. 如果用户登录错误次数过多, 则需要验证码, 若 captcha 验证不通过, 则直接返回
            if (Optional.ofNullable(user.getLoginErrorNum()).orElse(0) >= GlobalConfig.getMaxLoginWithNoCaptchaCode()) {
                if (captchaCheck.isNotSuccess()) {
                    CpRequestUtils.setNeedCaptcha(request, true);
                    throw new AuthenticationException(new PostException(captchaCheck));
                }
            }
            // 2. 查看账户是否已经被禁用
            if (DicCommon.State.disable.isValue(user.getState())) {
                throw new AuthenticationException(new PostException(ELoginPostCode.ACCOUNT_IS_DISABLED));
            }
            // 3. 查看账户是否被锁定
            if (DicAccUser.LockType.disable.isValue(user.getLockType())) {
                throw new AuthenticationException(new PostException(ELoginPostCode.ACCOUNT_IS_LOCKED));
            }
        } catch (AuthenticationException e) {
            // 登录失败后, 执行失败处理逻辑成功后, 依然返回 true, 执行 controller 的 `/login`
            log.info("登录失败 : {} ==> AuthenticationException : {} ", token.getUsername(), e.getClass().getName());
            return this.onLoginFailure(token, e, request, response);
        }
        return this.onLoginSuccess(token, subject, request, response);
    }

    /**
     * 登录失败执行逻辑, 如果返回 true, 则将继续走过滤器, controller, 返回false 则不再走接下来的过滤器.
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        // 1. 记录登录错误次数 + 1
        final AccUser user = CpSessionUtils.getUser();
        if (user != null) {
            final int loginErrorNum = Optional.of(user.getLoginErrorNum()).orElse(0) + 1;
            if (loginErrorNum >= GlobalConfig.getTimeOfLockWhenLoginFailure()) {
                user.setLockType(DicAccUser.LockType.disable.value());
            }
            user.setLoginErrorNum(loginErrorNum);
            iAccUser.updateByPrimaryKey(user);
        }
        // 2. 清除 session 等
        // 3. 如果原先已登录, 则执行退出登录方法
        SecurityUtils.getSubject().logout();
        // 执行父类 onLoginFailure 方法, 若不执行该方法, 则视为登录成功
        super.onLoginFailure(token, e, request, response);

        // 根据实际情况, 抛出 PostException
        if (e instanceof UnknownAccountException) {
            throw new PostException(ELoginPostCode.USER_NOT_FOUND);
        } else if (e instanceof IncorrectCredentialsException) {
            throw new PostException(ELoginPostCode.USER_OR_PASSWORD_ERROR);
        }
        final Throwable cause = e.getCause();
        if (cause instanceof PostException) {
            throw (PostException)cause;
        }
        throw e;
    }

    /**
     * 登录成功执行逻辑
     * 父类方法,里面有 issueSuccessRedirect(request, response); 是登录成功之后, 强制跳转至登录页面, 现取消这句话.
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
        log.debug("login success ==> user: {} ", token.getPrincipal());
        try {
            // 1. 重置登录次数
            final AccUser user = CpSessionUtils.getUser();
            user.setLoginErrorNum(0);
            iAccUser.updateByPrimaryKey(user);
            return false;
        } catch (Exception e) {
            log.error(e.getMessage());
            return onLoginFailure(token, new AuthenticationException("登录成功后响应失败", e), request, response);
        }
    }

    /**
     * 判断是否是 ajax 请求
     */
    private boolean isAjaxRequest(ShiroHttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(requestedWith);
    }

}
