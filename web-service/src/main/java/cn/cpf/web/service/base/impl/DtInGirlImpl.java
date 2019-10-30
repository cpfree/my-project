package cn.cpf.web.service.base.impl;

import cn.cpf.web.dal.base.DtInGirlMapper;
import cn.cpf.web.base.model.entity.DtInGirl;
import cn.cpf.web.base.model.example.DtInGirlExample;
import cn.cpf.web.service.base.api.IDtInGirl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (DtInGirl)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class DtInGirlImpl implements IDtInGirl {
    @Resource
    private DtInGirlMapper dtInGirlMapper;
            
    /**
     * 通过 id 查询单条数据
     *
     * @param id 唯一主键
     * @return 实例对象
     */
    @Override
    public DtInGirl findByPrimaryKey(String id) {
        return dtInGirlMapper.selectByPrimaryKey(id);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<DtInGirl> selectByExample(DtInGirlExample example) {
        return dtInGirlMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param dtInGirl 实例对象
     * @return 实例对象
     */
    @Override
    public DtInGirl insert(DtInGirl dtInGirl) {
        dtInGirlMapper.insert(dtInGirl);
        return dtInGirl;
    }

    /**
     * 修改数据
     *
     * @param dtInGirl 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(DtInGirl dtInGirl) {
        return dtInGirlMapper.updateByPrimaryKeySelective(dtInGirl);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String id) {
        return dtInGirlMapper.deleteByPrimaryKey(id) > 0;
    }
}