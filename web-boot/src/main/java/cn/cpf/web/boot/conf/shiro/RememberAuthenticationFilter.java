package cn.cpf.web.boot.conf.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * <b>Description : </b> 
 *
 * @author CPF
 * @date 2019/10/29 23:44
 **/
@Slf4j
public class RememberAuthenticationFilter extends FormAuthenticationFilter {


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Subject subject = getSubject(request, response);
        String redirectUrl = req.getRequestURI();
        log.debug("action function is :" + redirectUrl);

//        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute(IConstConfig.SESSION_USER_INFO);
//
//        try {
//            // 如果 isAuthenticated 为 false 证明不是登录过的，同时 isRememberd 为true
//            // 证明是没登陆直接通过记住我功能进来的
//            if (!subject.isAuthenticated() && subject.isRemembered()) {
//                // 获取session看看是不是空的
//                // 随便拿session的一个属性来看session当前是否是空的
//                if (userInfo == null) {
//                    String phone = subject.getPrincipal().toString();
//                    Objects.requireNonNull(phone);
//                    ItyUser user = userService.getRealUserLoginByPhone(phone);
//                    Objects.requireNonNull(user);
//                    userInfo = userService.convertUserInfo(user);
//                    userService.resetErrorCount(user.getUuid());
//                }
//                userService.loginSession(req, session, userInfo);
//            } else if (!subject.isAuthenticated() && !subject.isRemembered() && userInfo == null) {
//                userService.logloginSession(session);
//            } else if (subject.isAuthenticated() && !subject.isRemembered() && userInfo == null) {
//                userService.logloginSession(session);
//            }
//            if (userInfo == null) {
//                subject.logout();
//                return false;
//            } else {
//                return true;
//            }
//        } catch (Exception e) {
//            log.debug("logout session invalidate come login without uuid validate");
//        }
        return true;
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String loginUrl = "/login";
        WebUtils.issueRedirect(request, response, loginUrl);
    }

}
