package cn.cpf.web.service.combine.api;

import cn.cpf.web.base.constant.dic.DicCommon;
import cn.cpf.web.base.model.entity.AccLinkRolePerm;
import cn.cpf.web.base.model.entity.AccLinkUserRole;

import java.util.List;
import java.util.Set;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 10:05
 **/
public interface IAccessCombine {

    /**
     * 通过用户标识获取所有角色
     *
     * @param userGuid 用户标识
     * @return 角色名称
     */
    Set<String> selectAllRoleByUserGuid(String userGuid);

    /**
     * 通过用户标识获取所有角色
     *
     * @param state 查询角色权限关系
     * @param userGuid 用户标识
     * @return 角色名称
     */
    List<AccLinkUserRole> selectAllRoleByUserGuid(DicCommon.State state, String userGuid);

    /**
     * 查询角色权限关系
     *
     * @param state 查询角色权限关系
     * @return 角色权限关系列表
     */
    List<AccLinkRolePerm> selectLinkRolePerm(DicCommon.State state, String roleKey);
}
