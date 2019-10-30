package cn.cpf.web.boot.conf.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CpLogoutFilter extends LogoutFilter {

    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String redirectUrl = req.getHeader("referer");
        if (redirectUrl == null || redirectUrl.contains("index") || redirectUrl.contains("relogin")) {
            redirectUrl = "/login";
            if (session != null) {
                // TODO 清除session内容
            }
        }
        return redirectUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String redirectUrl = getRedirectUrl(request, response, subject);
        subject.logout();
        issueRedirect(request, response, redirectUrl);
        return false;
    }

}
