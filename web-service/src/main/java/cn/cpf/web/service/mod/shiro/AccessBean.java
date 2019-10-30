package cn.cpf.web.service.mod.shiro;

import cn.cpf.web.base.model.entity.AccUser;
import lombok.Data;

import java.util.Set;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 11:47
 **/
@Data
public class AccessBean {

    private AccUser user;

    /**
     * 当前用户所有角色
     */
    private Set<String> roles;

    /**
     * 当前用户所有权限
     */
    private Set<String> perms;

}
