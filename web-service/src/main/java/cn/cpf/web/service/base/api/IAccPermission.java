package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.entity.AccPermission;
import cn.cpf.web.base.model.example.AccPermissionExample;
import java.util.List;
import java.util.Map;

/**
 * 权限表, 缩写 perm(AccPermission)表服务接口
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
public interface IAccPermission {
          
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    AccPermission findByPrimaryKey(String guid);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<AccPermission> selectByExample(AccPermissionExample example);
    
    /**
     * 新增数据
     *
     * @param accPermission 实例对象
     * @return 实例对象
     */
    AccPermission insert(AccPermission accPermission);

    /**
     * 修改数据
     *
     * @param accPermission 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(AccPermission accPermission);

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String guid);

}