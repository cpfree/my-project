package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.base.model.example.AccUserExample;
import java.util.List;
import java.util.Map;

/**
 * 用户表, 缩写 user(AccUser)表服务接口
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
public interface IAccUser {
          
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    AccUser findByPrimaryKey(String guid);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<AccUser> selectByExample(AccUserExample example);
    
    /**
     * 新增数据
     *
     * @param accUser 实例对象
     * @return 实例对象
     */
    AccUser insert(AccUser accUser);

    /**
     * 修改数据
     *
     * @param accUser 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(AccUser accUser);

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String guid);

    /**
     * 通过用户名查找数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    AccUser findByUserName(String username);

    /**
     * 通过手机号查找数据
     *
     * @param phone 手机号
     * @return 实例对象
     */
    AccUser findByPhone(String phone);

    /**
     * 通过邮箱查找数据
     *
     * @param email 邮箱
     * @return 实例对象
     */
    AccUser findByEmail(String email);

    /**
     * 通过昵称查找数据
     *
     * @param nickname 昵称
     * @return 实例对象
     */
    AccUser findByNickname(String nickname);

    /**
     * 通过QQ号查找数据
     *
     * @param qqNo QQ号
     * @return 实例对象
     */
    AccUser findByQqNo(String qqNo);

}