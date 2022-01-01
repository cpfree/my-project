package cn.cpf.web.boot.conf.shiro;

import cn.cpf.web.boot.constant.PageTree;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <b>Description : </b> 登出过滤器
 *
 * 1.shiro有许多过滤器，其中就有一个LogoutFilter的过滤器，只需要继承并重写preHandle方法
 * 2.在配置文件中注入自定义实现的过滤器，在此配置当退出的时候应重定向到某个页面
 * 3.注入自定义filter，并且配置过滤器链
 * 4.直接在页面中配置相应退出链接
 *
 * @author CPF
 * @date 2019/10/31 14:31
 **/
@Component("scLogout")
public class ScLogoutFilter extends LogoutFilter {

    /**
     * 初始设定登出后跳转的url, 默认为 "/"
     */
    public ScLogoutFilter() {
        this.setRedirectUrl("/login");
    }

    /**
     * 这里可以通过函数形式配置登出后跳转的url
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        return super.getRedirectUrl();
    }

    /**
     * 在这里执行退出系统前需要清空的数据
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        return super.preHandle(request, response);
//        Subject subject=getSubject(request,response);
//        String redirectUrl=getRedirectUrl(request,response,subject);
//        subject.logout();
//        issueRedirect(request, response, redirectUrl);
    }

}
