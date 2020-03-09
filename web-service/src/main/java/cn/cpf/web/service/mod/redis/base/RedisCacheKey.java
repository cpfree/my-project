package cn.cpf.web.service.mod.redis.base;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/9/30 14:35
 **/
public interface RedisCacheKey {

    enum BeanListCacheKey {
        ImQqUser,
        ImQqGroup,
        ImQqGroupMember,
        ItyUserCache,
    }

    enum CacheKey {
        ImQqGroupMemberRelation,
        ImQqRelation,
        DictUpdateTime,
        dictType
    }

}
