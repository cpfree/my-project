package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.entity.AccLinkUserRole;
import cn.cpf.web.base.model.example.AccLinkUserRoleExample;
import java.util.List;
import java.util.Map;

/**
 * 用户角色关系表(AccLinkUserRole)表服务接口
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
public interface IAccLinkUserRole {
          
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    AccLinkUserRole findByPrimaryKey(String guid);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<AccLinkUserRole> selectByExample(AccLinkUserRoleExample example);
    
    /**
     * 新增数据
     *
     * @param accLinkUserRole 实例对象
     * @return 实例对象
     */
    AccLinkUserRole insert(AccLinkUserRole accLinkUserRole);

    /**
     * 修改数据
     *
     * @param accLinkUserRole 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(AccLinkUserRole accLinkUserRole);

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String guid);

}