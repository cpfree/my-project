package cn.cpf.web.base.lang.dict;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/13 16:31
 **/
@Slf4j
public class DictHandler {

    private static DictDbIoHandle dbIoHandle;

    private static DictCacheIoHandle cacheIoHandle;

    public static void register(DictDbIoHandle dbIoHandle, DictCacheIoHandle cacheIoHandle) {
        DictHandler.cacheIoHandle = cacheIoHandle;
        register(dbIoHandle);
    }

    public static void register(DictDbIoHandle dbIoHandle) {
        DictHandler.dbIoHandle = dbIoHandle;
        initDictCachePool();
    }




    /**
     * 初始化线程池
     */
    private static void initDictCachePool() {
        Map<String, DictTypeBean> map = null;
        // 通过时间判断redis里面数据是否完整;
        if (cacheIoHandle != null && cacheIoHandle.existDictData()) {
            map = cacheIoHandle.readAllFromCache();
        } else {
            map = dbIoHandle.queryAllDataFromDb();
            // 再次判断redis里面数据情况, 考虑加锁情况
            if (cacheIoHandle != null && cacheIoHandle.existDictData()) {
                // 1. 如果没有数据, 则塞入数据
                cacheIoHandle.writeAllToCache(map);
            }
            // 2. 如果正在被塞入数据, 则可以开额外线程判断redis数据是否完整
        }
        if (map == null || map.isEmpty()) {
            log.warn("未获取到数据");
        } else {
            // 塞入pool
            ConfigurableDictPool.putAllType(map);
        }
    }


    public static DictItemBean getDictItemBean(IDictItem iDictItem) {
        // 如果配置为空, 则从数据库获取无意义
        // TODO 考虑各个系统配置不一样的情况
        if (dbIoHandle != null) {
            final DictTypeBean dictTypeBean = getDictTypeBean(iDictItem.fieldKey());
            if (dictTypeBean != null) {
                return dictTypeBean.getDictItemBean(iDictItem);
            }
        }
        return StaticDictPool.getCodeItem(iDictItem);
    }


    public static DictTypeBean getDictTypeBean(String fieldKey) {
        if (dbIoHandle != null) {
            // 从pool里面获取, 获取到则返回
            DictTypeBean type = ConfigurableDictPool.getType(fieldKey);
            if (type != null) {
                return type;
            }
            // 如果缓存可用
            if (cacheIoHandle != null) {
                // 从redis里面获取, 获取成功后存入本地pool, 再从pool中获取
                type = cacheIoHandle.readOneFromCache(fieldKey);
                if (type != null) {
                    ConfigurableDictPool.putType(fieldKey, type);
                    return ConfigurableDictPool.getType(fieldKey);
                }
            }
            // 从数据库中获取
            type = dbIoHandle.queryOneDataFromDb(fieldKey);
            if (type != null) {
                if (cacheIoHandle != null) {
                    cacheIoHandle.writeOneToCache(type);
                }
                ConfigurableDictPool.putType(fieldKey, type);
                return ConfigurableDictPool.getType(fieldKey);
            }
            log.warn("获取错误");
        }
        return null;
    }

}
