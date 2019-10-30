package cn.cpf.web.service.base.impl;

import cn.cpf.web.base.model.entity.AccUser;
import cn.cpf.web.base.model.example.AccUserExample;
import cn.cpf.web.base.util.sql.DalUtils;
import cn.cpf.web.dal.base.AccUserMapper;
import cn.cpf.web.service.base.api.IAccUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表, 缩写 user(AccUser)表服务实现类
 *
 * @author CPF
 * @since 2019-10-25 10:52:17
 */
@Service
public class AccUserImpl implements IAccUser {
    @Resource
    private AccUserMapper accUserMapper;
            
    /**
     * 通过 guid 查询单条数据
     *
     * @param guid 全局唯一标识
     * @return 实例对象
     */
    @Override
    public AccUser findByPrimaryKey(String guid) {
        return accUserMapper.selectByPrimaryKey(guid);
    }
  
    /**
     * 查询列表数据
     *
     * @param example 查询实例
     * @return 对象列表
     */
    @Override
    public List<AccUser> selectByExample(AccUserExample example) {
        return accUserMapper.selectByExample(example);
    }
    
    /**
     * 新增数据
     *
     * @param accUser 实例对象
     * @return 实例对象
     */
    @Override
    public AccUser insert(AccUser accUser) {
        accUserMapper.insert(accUser);
        return accUser;
    }

    /**
     * 修改数据
     *
     * @param accUser 实例对象
     * @return 实例对象
     */
    @Override
    public int updateByPrimaryKey(AccUser accUser) {
        return accUserMapper.updateByPrimaryKeySelective(accUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param guid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByPrimaryKey(String guid) {
        return accUserMapper.deleteByPrimaryKey(guid) > 0;
    }

    /**
     * 通过用户名查找数据
     *
     * @param username 用户名
     * @return 实例对象
     */
    @Override
    public AccUser findByUserName(String username) {
        AccUserExample example = new AccUserExample();
        example.createCriteria().andNameEqualTo(username);
        final List<AccUser> accUsers = accUserMapper.selectByExample(example);
        return DalUtils.getAncCheckOne(accUsers);
    }

    /**
     * 通过手机号查找数据
     *
     * @param phone 手机号
     * @return 实例对象
     */
    @Override
    public AccUser findByPhone(String phone) {
        AccUserExample example = new AccUserExample();
        example.createCriteria().andPhoneEqualTo(phone);
        final List<AccUser> accUsers = accUserMapper.selectByExample(example);
        return DalUtils.getAncCheckOne(accUsers);
    }

    /**
     * 通过邮箱查找数据
     *
     * @param email 邮箱
     * @return 实例对象
     */
    @Override
    public AccUser findByEmail(String email) {
        AccUserExample example = new AccUserExample();
        example.createCriteria().andEmailEqualTo(email);
        final List<AccUser> accUsers = accUserMapper.selectByExample(example);
        return DalUtils.getAncCheckOne(accUsers);
    }

    /**
     * 通过昵称查找数据
     *
     * @param nickname 昵称
     * @return 实例对象
     */
    @Override
    public AccUser findByNickname(String nickname) {
        AccUserExample example = new AccUserExample();
        example.createCriteria().andNicknameEqualTo(nickname);
        final List<AccUser> accUsers = accUserMapper.selectByExample(example);
        return DalUtils.getAncCheckOne(accUsers);
    }

    /**
     * 通过QQ号查找数据
     *
     * @param qqNo QQ号
     * @return 实例对象
     */
    @Override
    public AccUser findByQqNo(String qqNo) {
        AccUserExample example = new AccUserExample();
        example.createCriteria().andQqNoEqualTo(qqNo);
        final List<AccUser> accUsers = accUserMapper.selectByExample(example);
        return DalUtils.getAncCheckOne(accUsers);
    }

}