package cn.cpf.web.boot.util;

import cn.cpf.web.base.model.entity.AccUser;
import lombok.NonNull;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/31 16:24
 **/
public class CpSessionUtils {

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

    /**
     * 设置用户对象
     */
    public static void setUser(@NonNull AccUser accUser) {
//        SecurityUtils.getSubject().getSession().setAttribute(USER_INFO_KEY, accUser);
    }

    /**
     * 获取用户对象
     */
    public static AccUser getUser() {
//        return (AccUser) SecurityUtils.getSubject().getSession().getAttribute(USER_INFO_KEY);
        return null;
    }

    /**
     * 获取用户标识
     */
    public static String getUserGuid() {
        return getUser().getGuid();
    }

//    public static boolean hasRole(String roles) {
//        return SecurityUtils.getSubject().hasRole(roles);
//    }

}
