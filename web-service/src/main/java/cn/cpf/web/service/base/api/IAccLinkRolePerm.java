package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.entity.AccLinkRolePerm;
import cn.cpf.web.base.model.example.AccLinkRolePermExample;
import java.util.List;
import java.util.Map;

/**
 * 角色权限关系表, 角色和权限的相关关系, 一个角色应该拥有哪些权限(AccLinkRolePerm)表服务接口
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
public interface IAccLinkRolePerm {
          
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    AccLinkRolePerm findByPrimaryKey(String guid);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<AccLinkRolePerm> selectByExample(AccLinkRolePermExample example);
    
    /**
     * 新增数据
     *
     * @param accLinkRolePerm 实例对象
     * @return 实例对象
     */
    AccLinkRolePerm insert(AccLinkRolePerm accLinkRolePerm);

    /**
     * 修改数据
     *
     * @param accLinkRolePerm 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(AccLinkRolePerm accLinkRolePerm);

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String guid);

}