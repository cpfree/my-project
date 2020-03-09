package cn.cpf.web.service.mod.redis.base;

import lombok.NonNull;
import org.springframework.data.redis.connection.DataType;

/**
 * <b>Description : </b> 缓存配置
 *
 * @author CPF
 * @date 2019/10/10 10:18
 **/
public class RedisKeyConfig {

    /**
     * 枚举名称
     */
    private String name;
    /**
     * 枚举类型
     */
    private DataType dataType;
    /**
     * 缓存失效策略
     */
    private CacheExpiredStrategy strategy;

    public RedisKeyConfig(@NonNull String name, @NonNull DataType dataType, CacheExpiredStrategy strategy) {
        this.name = name;
        this.dataType = dataType;
        this.strategy = strategy;
    }

    public RedisKeyConfig(String name, DataType dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public boolean isPersist() {
        return strategy == null;
    }

    public CacheExpiredStrategy getStrategy() {
        return strategy;
    }

}
