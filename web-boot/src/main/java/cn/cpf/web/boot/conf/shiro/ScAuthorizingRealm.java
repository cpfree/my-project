package cn.cpf.web.boot.conf.shiro;

import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.boot.util.CpSessionUtils;
import cn.cpf.web.service.base.api.IAccUser;
import cn.cpf.web.service.logic.api.IAccessLogic;
import cn.cpf.web.service.mod.system.shiro.AccessBean;
import com.github.cosycode.common.validate.RegexValidateUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <b>Description : </b> shiro 的 Realm 实现
 * <p>
 * <b>created in </b> 2019/10/29 23:43
 * </p>
 *
 * @author CPF
 **/
@Slf4j
@Component
public class ScAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private IAccUser iAccUser;
    @Autowired
    private IAccessLogic iAccessLogic;

    /**
     * 自定义密码匹配器
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new MySaltCredentialsMatcher());
    }

    /**
     * doGetAuthorizationInfo方法是在我们调用SecurityUtils.getSubject().isPermitted（）这个方法时会调用
     * 在某个方法上加上@RequiresPermissions这个，那么我们访问这个方法的时候，
     * 就会自动调用SecurityUtils.getSubject().isPermitted（），从而区调用doGetAuthorizationInfo 匹配
     *
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String name = (String) principals.getPrimaryPrincipal();
        // TODO 优化, 直接通过用户名获取
        @NonNull final AccUser user = iAccUser.findByUserName(name);

        final AccessBean bean = iAccessLogic.selectAccessBeanByUserGuid(user.getGuid());

        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        auth.setRoles(bean.getRoles());
        auth.setStringPermissions(bean.getPerms());
        return auth;
    }

    /**
     * 在用户登录的时候调用的也就是执行 SecurityUtils.getSubject().login（）的时候调用；(即:登录验证)
     *
     * 该步骤用于获取数据库中存放的用户数据信息, 之后封装为 AuthenticationInfo, 真正的验证密码操作是在 CredentialsMatcher 里验证的.
     *
     * @param authenticationToken 前台传入的账户请求数据封装而成的 Token
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        final String username = token.getUsername();
        AccUser user;
        // 通过用户名寻找出用户
        if (RegexValidateUtils.isPhone(username)) {
            // 如果是手机号码
            user = iAccUser.findByPhone(username);
        } else if (RegexValidateUtils.isEmail(username)) {
            // 如果是电子邮箱
            user = iAccUser.findByEmail(username);
        } else {
            // 如果是用户名
            user = iAccUser.findByUserName(username);
        }
        if (user != null) {
            // 存储 session
            CpSessionUtils.setUser(user);
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
        return null;
    }

}
