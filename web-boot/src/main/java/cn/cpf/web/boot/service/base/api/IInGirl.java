package cn.cpf.web.boot.service.base.api;

import cn.cpf.web.base.model.entity.InGirl;
import cn.cpf.web.base.model.entity.InGirlExample;

import java.util.List;
import java.util.Map;

/**
 * (InGirl)表服务接口
 *
 * @author CPF
 * @since 2019-10-20 14:25:39
 */
public interface IInGirl {
          
    /**
     * 通过 id 查询单条数据
     *
     * @param id 唯一主键
     * @return 实例对象
     */
    InGirl findByPrimaryKey(String id);
  
    /**
     * 查询多条数据
     *
     * @param conditionMap 查询条件
     * @return 对象列表
     */
    List<InGirl> selectAll(Map<String, Object> conditionMap);

    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<InGirl> selectByExample(InGirlExample example);
    
    /**
     * 新增数据
     *
     * @param inGirl 实例对象
     * @return 实例对象
     */
    InGirl insert(InGirl inGirl);

    /**
     * 修改数据
     *
     * @param inGirl 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(InGirl inGirl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String id);

}