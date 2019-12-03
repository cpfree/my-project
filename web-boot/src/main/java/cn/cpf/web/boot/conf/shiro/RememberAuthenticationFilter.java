package cn.cpf.web.boot.conf.shiro;

import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.boot.util.CpSessionUtils;
import cn.cpf.web.service.base.api.IAccUser;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <b>Description : </b> 
 *
 * shiro有几种默认的拦截器，authc,anno,roles,user等 authc就是FormAuthenticationFilter的实例
 *
 * @author CPF
 * @date 2019/10/29 23:44
 **/
@Slf4j
@Component
public class RememberAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private IAccUser iAccUser;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        String redirectUrl = req.getRequestURI();
        log.debug("action function is :" + redirectUrl);

        final Subject subject = SecurityUtils.getSubject();


        try {
            AccUser userInfo = CpSessionUtils.getUser();
            // 如果 isAuthenticated 为 false 证明不是登录过的，同时 isRemembered 为true
            // 证明是没登陆直接通过记住我功能进来的
            if (!subject.isAuthenticated() && subject.isRemembered()) {
                // 获取session看看是不是空的
                // 随便拿session的一个属性来看session当前是否是空的
                if (userInfo == null) {
                    @NonNull String userName = subject.getPrincipal().toString();
                    @NonNull AccUser user = iAccUser.findByUserName(userName);
                    // 更新用户
                    CpSessionUtils.setUser(user);
                    // 通过记住我进来的人不重置登录错误次数
                }
            }
        } catch (Exception e) {
            subject.logout();
            log.debug("logout session invalidate come login without uuid validate");
        }
        return true;
    }


}
