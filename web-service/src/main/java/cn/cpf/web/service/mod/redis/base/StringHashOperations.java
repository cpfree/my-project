package cn.cpf.web.service.mod.redis.base;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/9/29 18:28
 **/
public interface StringHashOperations<HK, HV> {

    HashOperations<String, HK, HV> getOpsForHash();

    /**
     * 获取redis 缓存的Key
     */
    String getCacheKey();

    default Long delete(Object... keys) {
        return getOpsForHash().delete(getCacheKey(), keys);
    }

    default Boolean hasKey(Object keys) {
        return getOpsForHash().hasKey(getCacheKey(), keys);
    }

    default HV get(Object keys) {
        return getOpsForHash().get(getCacheKey(), keys);
    }


    default List<HV> multiGet(Collection<HK> keys) {
        return getOpsForHash().multiGet(getCacheKey(), keys);
    }


    default Long increment(HK keys, long var3) {
        return getOpsForHash().increment(getCacheKey(), keys, var3);
    }


    default Double increment(HK keys, double var3) {
        return getOpsForHash().increment(getCacheKey(), keys, var3);
    }


    default Set<HK> keys() {
        return getOpsForHash().keys(getCacheKey());
    }


    default Long lengthOfValue(HK keys) {
        return getOpsForHash().lengthOfValue(getCacheKey(), keys);
    }


    default Long size() {
        return getOpsForHash().size(getCacheKey());
    }


    default void putAll(Map<? extends HK, ? extends HV> keys) {
        getOpsForHash().putAll(getCacheKey(), keys);
    }


    default void put(HK keys, HV var3) {
        getOpsForHash().put(getCacheKey(), keys, var3);
    }


    default Boolean putIfAbsent(HK keys, HV var3) {
        return getOpsForHash().putIfAbsent(getCacheKey(), keys, var3);
    }


    default List<HV> values() {
        return getOpsForHash().values(getCacheKey());
    }


    default Map<HK, HV> entries() {
        return getOpsForHash().entries(getCacheKey());
    }

    default Cursor<Map.Entry<HK, HV>> scan(ScanOptions keys) {
        return getOpsForHash().scan(getCacheKey(), keys);
    }

    default RedisOperations<String, ?> getOperations() {
        return getOpsForHash().getOperations();
    }

}

