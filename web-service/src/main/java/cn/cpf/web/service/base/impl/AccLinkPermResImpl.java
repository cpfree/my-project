package cn.cpf.web.service.base.impl;

import cn.cpf.web.dal.base.AccLinkPermResMapper;
import cn.cpf.web.base.model.entity.AccLinkPermRes;
import cn.cpf.web.base.model.example.AccLinkPermResExample;
import cn.cpf.web.service.base.api.IAccLinkPermRes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 权限资源关系表(AccLinkPermRes)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class AccLinkPermResImpl implements IAccLinkPermRes {
    @Resource
    private AccLinkPermResMapper accLinkPermResMapper;
            
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    @Override
    public AccLinkPermRes findByPrimaryKey(String guid) {
        return accLinkPermResMapper.selectByPrimaryKey(guid);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<AccLinkPermRes> selectByExample(AccLinkPermResExample example) {
        return accLinkPermResMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param accLinkPermRes 实例对象
     * @return 实例对象
     */
    @Override
    public AccLinkPermRes insert(AccLinkPermRes accLinkPermRes) {
        accLinkPermResMapper.insert(accLinkPermRes);
        return accLinkPermRes;
    }

    /**
     * 修改数据
     *
     * @param accLinkPermRes 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(AccLinkPermRes accLinkPermRes) {
        return accLinkPermResMapper.updateByPrimaryKeySelective(accLinkPermRes);
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String guid) {
        return accLinkPermResMapper.deleteByPrimaryKey(guid) > 0;
    }
}