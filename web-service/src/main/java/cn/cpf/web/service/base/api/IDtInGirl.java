package cn.cpf.web.service.base.api;

import cn.cpf.web.base.model.entity.DtInGirl;
import cn.cpf.web.base.model.example.DtInGirlExample;
import java.util.List;
import java.util.Map;

/**
 * (DtInGirl)表服务接口
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
public interface IDtInGirl {
          
    /**
     * 通过 id 查询单条数据
     *
     * @param id 唯一主键
     * @return 实例对象
     */
    DtInGirl findByPrimaryKey(String id);
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    List<DtInGirl> selectByExample(DtInGirlExample example);
    
    /**
     * 新增数据
     *
     * @param dtInGirl 实例对象
     * @return 实例对象
     */
    DtInGirl insert(DtInGirl dtInGirl);

    /**
     * 修改数据
     *
     * @param dtInGirl 实例对象
     * @return 实例对象
     */
    int updateByPrimaryKey(DtInGirl dtInGirl);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteByPrimaryKey(String id);

}