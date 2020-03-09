package cn.cpf.web.service.mod.redis.base;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/9/23 14:44
 **/
public abstract class AbstractBaseRedisDao<K, V> {

    @Resource
    protected RedisTemplate<String, V> redisTemplate;

    /**
     * 得到RedisSerializer
     *
     * @return
     */
    public RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

}