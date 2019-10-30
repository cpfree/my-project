package cn.cpf.web.boot.conf.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/29 23:44
 **/
@Slf4j
public class LoginAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws InvocationTargetException, IllegalAccessException {
        UsernamePasswordToken token = (UsernamePasswordToken) createToken(request, response);
        if (token == null) {
            String msg = "create AuthenticationToken error";
            throw new IllegalStateException(msg);
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            // 账户密码验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //账户密码验证失败
            log.error(e.getMessage());
            // TODO 失败状态, 并返回至前台
            return onLoginFailure(token, e, request, response);
        }


        // 检查用户状态
        // 检查登陆状态
        // 检查用户名密码
        // 检查验证码
        // 存储用户信息,以便于能够使用缓存
        return true;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
//        HttpServletRequest req = (HttpServletRequest) request;
//        UserInfo userInfo = ItySessionUtils.getUserInfo(req.getSession());
//
//        String phone = (String) token.getPrincipal();
//        userInfo.setUserName(phone);
//
//        if (StringUtils.isBlank(phone)) {
//            userService.loginFailSession(req, req.getSession(), userInfo);
//            return super.onLoginFailure(token, e, request, response);
//        }
//
//        // 增加错误登陆次数
//        Integer integer = userService.addLoginErrorCount(phone, IConstConfig.SHOP_ID);
//        userInfo.setErrorNumber(integer);
//
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//
//        //依据信息设置错误情况下的session，之后向页面跳转
//        userService.loginFailSession(req, req.getSession(), userInfo);
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) {
        try {
//            UserInfo userInfo = (UserInfo) subject.getSession().getAttribute(Constants.USER_INFO);
//            HttpServletRequest req = (HttpServletRequest) request;
//            super.setSuccessUrl(getAdminIndex());
//
//            userService.loginSuccess(req, userInfo.getMobile());
//            userService.loginSession(req, req.getSession(), userInfo);
            return super.onLoginSuccess(token, subject, request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return onLoginFailure(token, new AuthenticationException(), request, response);
        }
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        boolean isAllowed = isAccessAllowed(request, response, mappedValue);
        if (isAllowed && isLoginRequest(request, response)) {
            try {
                issueSuccessRedirect(request, response);
            } catch (Exception e) {
                log.error("", e);
            }
            return false;
        }
        return isAllowed || onAccessDenied(request, response, mappedValue);
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
//        String preurl = req.getParameter(IConstConfig.RETURN_URL);
        String preurl = req.getParameter("");
        String successurl = super.getSuccessUrl();
        if (StringUtils.isBlank(successurl)) {
            successurl = preurl;
            if (StringUtils.isBlank(successurl)) {
                successurl = "html";
            }
            WebUtils.getAndClearSavedRequest(request);
            WebUtils.issueRedirect(request, response, successurl, null, true);
            return;
        }
        if (successurl.contains("register")) {
            successurl = "/";
        }
        WebUtils.redirectToSavedRequest(req, res, successurl);
    }

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return pathsMatch(getLoginUrl(), request) || pathsMatch("/login", request);
    }


//    /**
//     * 检查验证码
//     */
//    private IPostCode checkKaptchaCode(ItyUser user, HttpServletRequest request) {
//        if (Optional.ofNullable(user.getLoginErrorNum()).orElse(0) >= 3) {
//            String checkCode = request.getParameter("captcha");
//            return PluginUtils.checkKaptchaCode(request, checkCode, user.getMobilePhone());
//        }
//        return ECommonPostCode.SUCCESS;
//    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

}
