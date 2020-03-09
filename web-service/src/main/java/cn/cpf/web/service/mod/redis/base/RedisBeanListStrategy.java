package cn.cpf.web.service.mod.redis.base;

import com.google.common.collect.Maps;
import lombok.NonNull;
import org.springframework.data.redis.core.*;

import java.util.*;

/**
 * <b>Description : </b>
 * <p>
 * 全部查询方案
 * 失效时间
 * 更新方案
 *
 * @author CPF
 * @date 2019/9/29 18:04
 **/
public abstract class RedisBeanListStrategy<HK, T> {

    private RedisTemplate<String, T> redisTemplate;

    private HashOperations<String, HK, T> opsForHash;

    private RedisKeyConfig keyConfig = getRedisKeyConfig();

    public RedisBeanListStrategy(@NonNull RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.opsForHash = redisTemplate.opsForHash();
    }

    /**
     * 获取key的配置信息
     */
    protected abstract RedisKeyConfig getRedisKeyConfig();

    /**
     * 主键策略(从一个对象中获取主键的方法)
     */
    protected abstract HK getHKey(T t);

    /**
     * 查询全部结果的方法
     */
    protected abstract List<T> selectAll();

    /**
     * 查询全部结果的方法
     */
    protected abstract T selectOne(HK hk);

    /**
     * 查询多个结果方法
     */
    protected abstract List<T> selectMulti(List<HK> hk);

    public void check() {
        // 如果缓存里面没有数据
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(getCacheKey()))) {
            // 查询出所有集合
            List<T> list = selectAll();
            // 清空缓存
            redisTemplate.delete(getCacheKey());
            updateList(list);
            // 非持久化缓存, 更新失效时间
            if (!keyConfig.isPersist()) {
                // 更新失效时间
                Date date = keyConfig.getStrategy().newDate();
                // 设置失效策略
                redisTemplate.expireAt(getCacheKey(), date);
            }
        }
    }

    /**
     * 更新单个对象
     */
    public void update(@NonNull T t) {
        HK apply = getHKey(t);
        put(apply, t);
    }

    /**
     * 更新全部结果的方法
     */
    public void updateList(@NonNull List<T> list) {
        Map<HK, T> allMap = caseRedisHashMap(list);
        // 将所有缓存放入 redis
        putAll(allMap);
    }

    /**
     * @param list 实体类集合
     */
    protected Map<HK, T> caseRedisHashMap(@NonNull List<T> list) {
        Map<HK, T> map = Maps.newHashMapWithExpectedSize(list.size());
        list.forEach(it -> {
            if (it == null) {
                return;
            }
            HK key = getHKey(it);
            if (key != null) {
                map.put(key, it);
            }
        });
        return map;
    }

    /**
     * 获取缓存Key
     */
    protected final String getCacheKey() {
        return keyConfig.getName();
    }

    /**
     * 删除hash缓存 HK
     */
    public Long delete(@NonNull HK... keys) {
        return opsForHash.delete(getCacheKey(), keys);
    }

    /**
     * 是否存在hash缓存
     */
    public Boolean hasKey(HK keys) {
        return opsForHash.hasKey(getCacheKey(), keys);
    }

    /**
     * 获取对象(缓存中获取不到则从数据库中获取)
     */
    public T get(HK key) {
        check();
        return opsForHash.get(getCacheKey(), key);
    }

    /**
     * 获取对象(仅仅从缓存中获取)
     */
    public T getFromCacheOnly(HK key) {
        check();
        T t = opsForHash.get(getCacheKey(), key);
        if (t == null) {
            t = selectOne(key);
            update(t);
        }
        return t;
    }

    /**
     * 获取多个对象(缓存中获取不到则从数据库中获取)
     */
    public List<T> multiGet(List<HK> keys) {
        check();
        List<T> list = opsForHash.multiGet(getCacheKey(), keys);
        List<HK> nullList = null;
        for (int i = 0, len = list.size(); i < len; i++) {
            T t = list.get(i);
            if (t == null) {
                if (nullList == null) {
                    nullList = new ArrayList<>();
                }
                nullList.add(keys.get(i));
            }
        }
        // 查询的值均存在
        if (nullList == null) {
            return list;
        }
        List<T> newList = selectMulti(nullList);
        Map<HK, T> hktMap = caseRedisHashMap(newList);
        putAll(hktMap);
        return newList;
    }

    /**
     * 获取多个对象(仅仅从缓存中获取)
     */
    public List<T> multiGetFromCacheOnly(List<HK> keys) {
        check();
        return opsForHash.multiGet(getCacheKey(), keys);
    }


    public Set<HK> keys() {
        check();
        return opsForHash.keys(getCacheKey());
    }

    public Long lengthOfValue(HK keys) {
        check();
        return opsForHash.lengthOfValue(getCacheKey(), keys);
    }

    public Long size() {
        check();
        return opsForHash.size(getCacheKey());
    }

    public void putAll(Map<? extends HK, ? extends T> keys) {
        opsForHash.putAll(getCacheKey(), keys);
    }

    public void put(HK keys, T var3) {
        opsForHash.put(getCacheKey(), keys, var3);
    }

    public Boolean putIfAbsent(HK keys, T var3) {
        return opsForHash.putIfAbsent(getCacheKey(), keys, var3);
    }

    public List<T> values() {
        check();
        return opsForHash.values(getCacheKey());
    }

    public Map<HK, T> entries() {
        return opsForHash.entries(getCacheKey());
    }

    public Cursor<Map.Entry<HK, T>> scan(ScanOptions keys) {
        return opsForHash.scan(getCacheKey(), keys);
    }

    public RedisOperations<String, ?> getOperations() {
        return opsForHash.getOperations();
    }

}
