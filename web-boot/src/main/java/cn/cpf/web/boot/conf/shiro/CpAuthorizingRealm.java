package cn.cpf.web.boot.conf.shiro;

import cn.cpf.web.base.constant.dic.DicCommon;
import cn.cpf.web.base.constant.postcode.ELoginPostCode;
import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.base.util.validate.RegexValidateUtils;
import cn.cpf.web.service.base.api.IAccUser;
import cn.cpf.web.service.logic.api.IAccessLogic;
import cn.cpf.web.service.mod.shiro.AccessBean;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/29 23:43
 **/
@Slf4j
public class CpAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    IAccUser iAccUser;
    @Autowired
    IAccessLogic iAccessLogic;

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
     * 在用户登录的时候调用的也就是执行SecurityUtils.getSubject().login（）的时候调用；(即:登录验证)
     *
     * // TODO 仅仅获取信息, 不验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        final String username = token.getUsername();
        AccUser user;
        if (RegexValidateUtils.isPhone(username)) {
            // 如果是手机号码
            user = iAccUser.findByPhone(username);
        } else if (RegexValidateUtils.isEamil(username)) {
            // 如果是电子邮箱
            user = iAccUser.findByEmail(username);
        } else {
            // 如果是用户名
            user = iAccUser.findByUserName(username);
        }
        if (user != null) {
            // TODO 如果当前用户以禁用的话, 那就没必要继续往下了
            if (DicCommon.State.disable.isCode(user.getState())) {
                if (user.getPassword().equals(String.valueOf(token.getPassword()))) {
                    throw new PostException(ELoginPostCode.ACCOUNT_IS_DISABLED);
                }
            }
            return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
        }
        return null;
    }

}
