package cn.cpf.web.service.mod.system.dict;

import cn.cpf.web.base.lang.dict.DictCacheIoHandle;
import cn.cpf.web.base.lang.dict.DictTypeBean;
import cn.cpf.web.service.mod.redis.base.RedisCacheKey;
import cn.cpf.web.service.mod.redis.base.RedisUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/18 11:51
 **/
public class DictCacheIoHandleImpl extends DictCacheIoHandle {


    @Override
    public Map<String, DictTypeBean> readAllFromCache() {
        return RedisUtils.doQueryHashData(RedisCacheKey.CacheKey.dictType, DictTypeBean::getFieldKey, null);
    }

    @Override
    public DictTypeBean readOneFromCache(String fieldKey) {
        final Map<String, DictTypeBean> map = RedisUtils.doQueryHashData(RedisCacheKey.CacheKey.dictType, DictTypeBean::getFieldKey, Arrays.asList(fieldKey));
        return map.get(fieldKey);
    }

    @Override
    public void writeAllToCache(Map<String, DictTypeBean> dataListMap) {
        RedisUtils.doPushHashData(RedisCacheKey.CacheKey.dictType, dataListMap);
    }

    @Override
    public void writeOneToCache(DictTypeBean data) {
        Map<String, DictTypeBean> map = new HashMap<>(1);
        map.put(data.getFieldKey(), data);
        RedisUtils.doPushHashData(RedisCacheKey.CacheKey.dictType, map);
    }

    /**
     * 判断redis里面数据是否存在
     * 1. redis里面没有相关键    : return false
     * 2. redis正在被其它线程塞数据 : 等待一秒, 再次验证, 若依然如此, 返回 false
     * 3. redis里面有完整数据 : return true
     * 4. redis里面有数据, 但是数据不全 return true// 该结果应该无法验证
     * 5. redis无法联通  : return false
     *
     * @param tagList
     * @return true表示可以取数据, false表示不能取数据
     */
    @Override
    public boolean existDictData(String... tagList) {
        return false;
    }

}
