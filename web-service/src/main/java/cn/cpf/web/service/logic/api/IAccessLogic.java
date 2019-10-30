package cn.cpf.web.service.logic.api;

import cn.cpf.web.service.mod.shiro.AccessBean;
import lombok.NonNull;

import java.util.Set;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 10:05
 **/
public interface IAccessLogic {

    /**
     * 通过角色名称获取所有有效权限
     *
     * @param userGuid 角色名称
     * @return AccessBean
     */
    AccessBean selectAccessBeanByUserGuid(String userGuid);

    /**
     * 通过角色, 获取有用的权限
     *
     * @param roleKeys 角色Set
     * @return 有用的权限Set
     */
    Set<String> getPermSetByRoleKey(@NonNull Set<String> roleKeys);

}
