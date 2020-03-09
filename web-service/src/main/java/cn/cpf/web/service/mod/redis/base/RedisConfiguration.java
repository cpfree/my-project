package cn.cpf.web.service.mod.redis.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/9/23 14:02
 **/
@Configuration
@Slf4j
public class RedisConfiguration {

    /**
     * redis服务器的host或者ip地址
     */
    @Value("${redis.hostName:}")
    private String hostName;
    /**
     * 服务的端口号
     */
    @Value("${redis.port:6379}")
    private int port;
    /**
     * 默认使用的数据库
     */
    @Value("${redis.database:0}")
    private int database;
    /**
     * 密码
     */
    @Value("${redis.password:}")
    private String password;
    /**
     * 连接池 : 最大连接数
     */
    @Value("${redis.maxToTal:20}")
    private int maxToTal;
    /**
     * 连接池 : 最大空闲数
     */
    @Value("${redis.maxIdle:10}")
    private int maxIdle;
    /**
     * 连接池 : 最小空闲数
     */
    @Value("${redis.minIdle:3}")
    private int minIdle;
    /**
     * 连接池 : 当池内没有可用的连接时，最大等待时间
     */
    @Value("${redis.maxWaitMillis:10}")
    private int maxWaitMillis;
    /**
     * 使用连接时是否测试可用
     */
    @Value("${redis.testOnBorrow:true}")
    private boolean testOnBorrow;

    @Bean("redisTemplate")
    public ObjectRedisTemplate<?> getObjectRedisTemplate() {
        log.info("redis hostName : {}, port : {}, password : {}, database : {}", hostName, port, password, database);
        final RedisConnectionFactory redisConnectionFactory = redisConnectionFactory();
        final ObjectRedisTemplate<?> objectObjectRedisTemplate = new ObjectRedisTemplate<>(redisConnectionFactory);
        return objectObjectRedisTemplate;
    }


    /**
     * jedis 连接工厂(使用连接池)
     */
    public RedisConnectionFactory redisConnectionFactory() {
        //单机版jedisCachingConfigurerSupport
        final RedisStandaloneConfiguration redisStandaloneConfiguration = RedisDataSources.geneRedisStandaloneConfiguration(hostName, port, password, database);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxToTal);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        final JedisClientConfiguration jedisClientConfiguration = RedisDataSources.geneJedisClientConfiguration(jedisPoolConfig);
        //单机配置 + 客户端配置 = jedis 连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }


}
