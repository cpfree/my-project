package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.entity.AccLinkPermRes;
import cn.cpf.web.base.model.example.AccLinkPermResExample;
import java.util.List;
import java.util.Map;

/**
 * 权限资源关系表(AccLinkPermRes)表服务接口
 *
 * @author CPF
 * @since 2019-10-25 10:52:16
 */
public interface IAccLinkPermRes {
          
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    AccLinkPermRes findByPrimaryKey(String guid);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<AccLinkPermRes> selectByExample(AccLinkPermResExample example);
    
    /**
     * 新增数据
     *
     * @param accLinkPermRes 实例对象
     * @return 实例对象
     */
    AccLinkPermRes insert(AccLinkPermRes accLinkPermRes);

    /**
     * 修改数据
     *
     * @param accLinkPermRes 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(AccLinkPermRes accLinkPermRes);

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String guid);

}