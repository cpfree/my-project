package cn.cpf.web.boot.util;

import cn.cpf.web.base.lang.base.IPostCode;
import cn.cpf.web.base.model.entity.AccUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/31 16:24
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CpSessionUtils {

    /**
     * 存储用户信息的 session Key
     */
    private static final String POST_CODE = "postcode";
    /**
     * 存储用户信息的 session Key
     */
    private static final String USER_INFO_KEY = "user_info";
    /**
     * 角色
     */
    private static final String ROLE_KEY = "role_key";
    /**
     * 权限
     */
    private static final String PERM_KEY = "perm_key";

    public static Session getSession() {
        final Session session = SecurityUtils.getSubject().getSession();
        System.out.println("fff " + session.getId());
        return session;
    }

    /**
     * 设置用户对象
     */
    public static void setUser(@NonNull AccUser accUser) {
        getSession().setAttribute(USER_INFO_KEY, accUser);
    }

    /**
     * 获取用户对象
     */
    public static AccUser getUser() {
        return (AccUser) getSession().getAttribute(USER_INFO_KEY);
    }

    /**
     * 获取用户标识
     */
    public static String getUserGuid() {
        return getUser().getGuid();
    }

    public static boolean hasRole(String roles) {
        return SecurityUtils.getSubject().hasRole(roles);
    }

}
