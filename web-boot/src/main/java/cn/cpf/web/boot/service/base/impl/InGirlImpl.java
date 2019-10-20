package cn.cpf.web.boot.service.base.impl;

import cn.cpf.web.base.model.entity.InGirl;
import cn.cpf.web.base.model.entity.InGirlExample;
import cn.cpf.web.boot.dao.base.InGirlMapper;
import cn.cpf.web.boot.service.base.api.IInGirl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (InGirl)表服务实现类
 *
 * @author CPF
 * @since 2019-10-20 14:25:41
 */
@Service
public class InGirlImpl implements IInGirl {
    @Resource
    private InGirlMapper inGirlMapper;
            
    /**
     * 通过 id 查询单条数据
     *
     * @param id 唯一主键
     * @return 实例对象
     */
    @Override
    public InGirl findByPrimaryKey(String id) {
        return inGirlMapper.selectByPrimaryKey(id);
    }
  
    /**
     * 查询多条数据
     *
     * @param conditionMap 查询条件
     * @return 对象列表
     */
    @Override
    public List<InGirl> selectAll(Map<String, Object> conditionMap) {
        //return inGirlMapper.selectAllByLimit(offset, limit);
        return new ArrayList();
    }

    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<InGirl> selectByExample(InGirlExample example) {
        return inGirlMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param inGirl 实例对象
     * @return 实例对象
     */
    @Override
    public InGirl insert(InGirl inGirl) {
        inGirlMapper.insert(inGirl);
        return inGirl;
    }

    /**
     * 修改数据
     *
     * @param inGirl 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(InGirl inGirl) {
        return inGirlMapper.updateByPrimaryKeySelective(inGirl);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String id) {
        return inGirlMapper.deleteByPrimaryKey(id) > 0;
    }
}