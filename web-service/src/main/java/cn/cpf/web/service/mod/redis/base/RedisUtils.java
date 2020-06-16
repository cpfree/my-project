package cn.cpf.web.service.mod.redis.base;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/8 13:56
 **/
@Slf4j
public class RedisUtils {

    /**
     * 将 list 按照指定规则转为 Map
     *
     * @param getHKey 从对象中寻求 hash key 的方法
     * @param dbList 待转换的list
     * @param <K> hash key
     * @param <V> hash value
     * @return 转换后的map
     */
    public static <K, V> Map<K, V> castListToMap(@NonNull Function<V, K> getHKey, @NonNull List<V> dbList) {
        if (dbList.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, V> map = Maps.newHashMapWithExpectedSize(dbList.size());
        dbList.forEach(it -> {
            if (it == null) {
                return;
            }
            K key = getHKey.apply(it);
            if (key != null) {
                map.put(key, it);
            }
        });
        return map;
    }


    /**
     * @param cacheKey      缓存Key
     * @param getHKey       从对象中寻求 hash key 的方法
     * @param queryKeys     查询Key
     * @param <K>           hash key
     * @param <V>           hash value
     * @return
     */
    public static <K, V> Map<K, V> doQueryHashData(@NonNull RedisCacheKey.CacheKey cacheKey, @NonNull Function<V, K> getHKey, List<K> queryKeys) {
        final ObjectRedisTemplate<?> redisTemplate = RedisDataSources.getInstance();
        // redis 不可用
        if (redisTemplate == null) {
            log.info("doQueryHashData -> disabled redis cacheKey : {}", cacheKey.name());
            return new HashMap<>();
        }
        long min = System.nanoTime();
        final HashOperations<String, K, V> operations = redisTemplate.opsForHash();
        Map<K, V> map = null;
        if (queryKeys == null) {
            log.info("doQueryHashData All start--> cacheKey : {}", cacheKey.name());
            map = operations.entries(cacheKey.name());
            log.info("doQueryHashData All end--> cacheKey : {}", cacheKey.name());
        } else {
            log.info("doQueryHashData part start--> cacheKey : {}, time -> {}", cacheKey.name(), (System.nanoTime() - min));
            List<V> list = operations.multiGet(cacheKey.name(), queryKeys);
            if (list != null) {
                map = RedisUtils.castListToMap(getHKey, list);
            }
            log.info("doQueryHashData part end--> cacheKey : {}, time -> {}", cacheKey.name(), (System.nanoTime() - min));
        }
        return Optional.ofNullable(map).orElse(new HashMap<>());
    }

    /**
     * @param cacheKey      缓存Key
     * @param map           推送数据
     * @param <K>           hash key
     * @param <V>           hash value
     * @return
     */
    public static <K, V> boolean doPushHashData(@NonNull RedisCacheKey.CacheKey cacheKey, @NonNull Map<K, V> map) {
        final ObjectRedisTemplate<?> redisTemplate = RedisDataSources.getInstance();
        // redis 不可用
        if (redisTemplate == null) {
            log.info("doPushHashData -> disabled redis cacheKey : {}", cacheKey.name());
            return false;
        }
        long min = System.nanoTime();
        final HashOperations<String, K, V> operations = redisTemplate.opsForHash();
        operations.putAll(cacheKey.name(), map);
        log.info("doPushHashData part end--> cacheKey : {}, time -> {}", cacheKey.name(), (System.nanoTime() - min));
        return true;
    }

    /**
     * @param cacheKey 缓存Key
     * @param keys 查询Key
     * @param getHKey      从对象中寻求 hash key 的方法
     * @param allSupplier 查询不到时到数据库中查询的方法
     * @param strategy 过期策略
     * @param <K> hash key
     * @param <V> hash value
     */
    public static <K, V> Map<K, V> queryHashData(@NonNull RedisCacheKey.CacheKey cacheKey, @NonNull Function<V, K> getHKey,
                                           @NonNull Supplier<List<V>> allSupplier,
                                           List<K> keys,
                                           @NonNull CacheExpiredStrategy strategy) {
        return queryHashData(cacheKey, getHKey, allSupplier, null, keys, strategy);
    }

    /**
     * @param cacheKey      缓存Key
     * @param getHKey       从对象中寻求 hash key 的方法
     * @param allSupplier   完全初始化缓存函数接口 : 用于缓存初始化, 或缓存失效后初始化
     * @param partUpdateFun 部分更新缓存函数接口 : 用于部分更新缓存
     * @param hKeyList      查询Key : 查询相关部分数据, 若为null或里面为空则查询全部数据
     * @param strategy      过期时间
     * @param <K>           hash key
     * @param <V>           hash value
     */
    public static <K, V> Map<K, V> queryHashData(@NonNull RedisCacheKey.CacheKey cacheKey, @NonNull Function<V, K> getHKey,
                                                 @NonNull Supplier<List<V>> allSupplier,
                                          Function<List<K>, List<V>> partUpdateFun, List<K> hKeyList, @NonNull CacheExpiredStrategy strategy) {
        long min = System.nanoTime();
        log.info("queryData start --> cacheKey : {}", cacheKey.name());
        final ObjectRedisTemplate<?> redisTemplate = RedisDataSources.getInstance();
        // redis 不可用
        if (redisTemplate == null) {
            final List<V> dbList;
            // 部分查询可用, 则进行部分查询, 否则进行完全查询
            if (partUpdateFun != null && hKeyList != null && !hKeyList.isEmpty()) {
                dbList = partUpdateFun.apply(hKeyList);
            } else {
                dbList = allSupplier.get();
            }
            if (dbList == null) {
                log.info("queryData -> disabled redis ==> select from db and result is empty --> cacheKey : {}, time -> {}", cacheKey.name(), (System.nanoTime() - min));
                return new HashMap<>();
            }
            log.info("queryData -> disabled redis --> cacheKey : {}, time -> {}", cacheKey.name(), (System.nanoTime() - min));
            return RedisUtils.castListToMap(getHKey, dbList);
        }
        final Map<K, V> map = queryHashDataByRedis(redisTemplate, cacheKey, getHKey, allSupplier, partUpdateFun, hKeyList, strategy);
        log.info("queryData end --> cacheKey : {}, time -> {}", cacheKey.name(), (System.nanoTime() - min));
        return map;
    }

    /**
     * @param cacheKey      缓存Key
     * @param getHKey       从对象中寻求 hash key 的方法
     * @param allSupplier   完全初始化缓存函数接口 : 用于缓存初始化, 或缓存失效后初始化
     * @param partUpdateFun 部分更新缓存函数接口 : 用于部分更新缓存
     * @param hKeyList      查询Key : 查询相关部分数据, 若为null或里面为空则查询全部数据
     * @param strategy      缓存策略
     * @param <K>           hash key
     * @param <V>           hash value
     */
    private static <K, V> Map<K, V> queryHashDataByRedis(RedisTemplate<String, ?> redisTemplate, @NonNull RedisCacheKey.CacheKey cacheKey,
                                                  @NonNull Function<V, K> getHKey,
                                                  @NonNull Supplier<List<V>> allSupplier,
                                                  Function<List<K>, List<V>> partUpdateFun,
                                                  List<K> hKeyList,
                                                  @NonNull CacheExpiredStrategy strategy) {
        // 使用缓存情况
        final HashOperations<String, K, V> operations = redisTemplate.opsForHash();
        final String name = cacheKey.name();
        // keys 为 null 表示获取全部
        if (hKeyList == null || hKeyList.isEmpty()) {
            Map<K, V> entries = operations.entries(name);
            if (entries == null || entries.isEmpty() || !Boolean.TRUE.equals(redisTemplate.hasKey(name))) {
                initCache(redisTemplate, cacheKey, allSupplier, getHKey, strategy);
                entries = operations.entries(name);
            }
            log.warn("queryData update and putAll end --> cacheKey : {}", cacheKey.name());
            return entries;
        }

        // 以下为获取部分情况
        // TODO 判断缓存是否有更新, 若有更新则更新缓存
        final List<V> vs = operations.multiGet(name, hKeyList);
        // 如果从缓存中获取的数据集全为空, 则可能这个 cacheKey 不存在. 则查询判断是否存在, 若的确不存在, 则完全更新缓存并返回
        if (vs.stream().allMatch(Objects::isNull) && !Boolean.TRUE.equals(redisTemplate.hasKey(name))) {
            // 初始化缓存
            initCache(redisTemplate, cacheKey, allSupplier, getHKey, strategy);
            // 重新从redis里面获取数据并返回
            final List<V> dbList = operations.multiGet(name, hKeyList);
            return RedisUtils.castListToMap(getHKey, dbList);
        }

        // 如果从缓存中获取的数据集有空, 同时部分查询可用, 则进行部分查询, 更新缓存, 并返回数据
        if (vs.stream().anyMatch(Objects::isNull) && partUpdateFun != null) {
            log.info("queryData partUpdateCache --> cacheKey : {}", cacheKey.name());
            return partUpdateCache(redisTemplate, cacheKey, hKeyList, partUpdateFun, getHKey);
        }
        // 返回数据
        return RedisUtils.castListToMap(getHKey, vs);
    }

    /**
     * 从数据库中完全更新缓存
     *
     * @param redisTemplate 句柄
     * @param cacheKey 缓存key
     * @param supplier 查询方法
     * @param getHKey 从对象中寻求 hash key 的方法
     * @param strategy 缓存策略
     * @param <K> hash key
     * @param <V> hash value
     */
    private static synchronized <K, V> void initCache(RedisTemplate<String, ?> redisTemplate, RedisCacheKey.CacheKey cacheKey, Supplier<List<V>> supplier,
                                                      Function<V, K> getHKey, @NonNull CacheExpiredStrategy strategy) {
        // 双重锁判断
        final String name = cacheKey.name();
        if (!Boolean.TRUE.equals(redisTemplate.hasKey(name))) {
            log.info("queryData initCache --> cacheKey : {}", cacheKey.name());
            final List<V> dbList = supplier.get();
            if (dbList == null) {
                log.info("initCache select from db and result is empty --> cacheKey : {}", name);
                return;
            }
            final Map<K, V> entries = RedisUtils.castListToMap(getHKey, dbList);
            redisTemplate.delete(name);
            redisTemplate.opsForHash().putAll(name, entries);
            redisTemplate.expireAt(name, strategy.newDate());
        }
    }

    /**
     * 从数据库中部分更新缓存
     *
     * @param redisTemplate 句柄
     * @param cacheKey 缓存key
     * @param keys 查询Key : 查询相关部分数据, 若为null或里面为空则查询全部数据
     * @param partUpdateFun 部分查询方法
     * @param getHKey 从对象中寻求 hash key 的方法
     * @param <K> hash key
     * @param <V> hash value
     * @return
     */
    private static <K, V> Map<K, V> partUpdateCache(RedisTemplate<String, ?> redisTemplate, RedisCacheKey.CacheKey cacheKey, List<K> keys, Function<List<K>,
            List<V>> partUpdateFun, Function<V, K> getHKey) {
        final String name = cacheKey.name();
        final List<V> dbList = partUpdateFun.apply(keys);
        if (dbList == null) {
            log.info("initCache select from db and result is empty --> cacheKey : {}", name);
            return new HashMap<>();
        }
        final Map<K, V> entries = RedisUtils.castListToMap(getHKey, dbList);
        redisTemplate.opsForHash().putAll(name, entries);
        return entries;
    }

}
