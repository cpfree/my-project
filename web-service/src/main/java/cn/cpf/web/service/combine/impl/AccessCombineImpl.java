package cn.cpf.web.service.combine.impl;

import cn.cpf.web.base.constant.dic.DicCommon;
import cn.cpf.web.base.model.entity.AccLinkRolePerm;
import cn.cpf.web.base.model.entity.AccLinkUserRole;
import cn.cpf.web.base.model.example.AccLinkRolePermExample;
import cn.cpf.web.base.model.example.AccLinkUserRoleExample;
import cn.cpf.web.dal.base.AccLinkRolePermMapper;
import cn.cpf.web.dal.base.AccLinkUserRoleMapper;
import cn.cpf.web.dal.combine.AccessCombineMapper;
import cn.cpf.web.service.combine.api.IAccessCombine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/27 10:06
 **/
@Service
@Slf4j
public class AccessCombineImpl implements IAccessCombine {

    @Autowired
    AccessCombineMapper mapper;
    @Autowired
    AccLinkRolePermMapper accLinkRolePermMapper;
    @Autowired
    AccLinkUserRoleMapper accLinkUserRoleMapper;

    /**
     * 通过用户标识获取所有角色
     *
     * @param userGuid 用户标识
     * @return 角色名称
     */
    @Override
    public Set<String> selectAllRoleByUserGuid(String userGuid) {
        return accLinkUserRoleMapper.selectAllActiveRoleNameByUserGuid(userGuid);
    }

    /**
     * 通过用户标识获取所有角色
     *
     * @param state 查询角色权限关系
     * @param userGuid 用户标识
     * @return 角色名称
     */
    @Override
    public List<AccLinkUserRole> selectAllRoleByUserGuid(DicCommon.State state, String userGuid) {
        AccLinkUserRoleExample example = new AccLinkUserRoleExample();
        final AccLinkUserRoleExample.Criteria criteria = example.createCriteria();
        if (state != null) {
            criteria.andStateEqualTo(state.getCode());
        }
        if (StringUtils.isNotBlank(userGuid)) {
            criteria.andUserGuidEqualTo(userGuid);
        }
        return accLinkUserRoleMapper.selectByExample(example);
    }

    /**
     * 查询角色权限关系
     *
     * @param state 查询角色权限关系
     * @return 角色权限关系列表
     */
    @Override
    public List<AccLinkRolePerm> selectLinkRolePerm(DicCommon.State state, String roleKey){
        AccLinkRolePermExample example = new AccLinkRolePermExample();
        final AccLinkRolePermExample.Criteria criteria = example.createCriteria();
        if (state != null) {
            criteria.andStateEqualTo(state.getCode());
        }
        if (StringUtils.isNotBlank(roleKey)) {
            criteria.andRoleKeyEqualTo(roleKey);
        }
        return accLinkRolePermMapper.selectByExample(example);
    }


}
