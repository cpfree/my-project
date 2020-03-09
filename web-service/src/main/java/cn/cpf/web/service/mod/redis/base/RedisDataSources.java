package cn.cpf.web.service.mod.redis.base;

import cn.cpf.web.service.mod.spring.util.SpringBeanUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/11 10:40
 **/
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisDataSources {

    /**
     * 生成单机配置
     */
    static RedisStandaloneConfiguration geneRedisStandaloneConfiguration(String hostName, int port, String password, int database) {
        //单机版jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        //设置redis服务器的host或者ip地址
        redisStandaloneConfiguration.setHostName(hostName);
        //设置默认使用的数据库
        redisStandaloneConfiguration.setDatabase(database);
        //设置密码
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        //设置redis的服务的端口号
        redisStandaloneConfiguration.setPort(port);
        return redisStandaloneConfiguration;
    }


    /**
     * 生成客户端配置
     *
     * @param jedisPoolConfig 连接池配置
     * @return
     */
    static JedisClientConfiguration geneJedisClientConfiguration(JedisPoolConfig jedisPoolConfig) {
        //获得默认的连接池构造器
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = builder.usePooling();
        jpcb.poolConfig(jedisPoolConfig);
        //通过构造器来构造 jedis 客户端配置
        return jpcb.build();
    }

    /**
     * @return 不被Spring管理的单实例多线程redis数据源
     */
    public static ObjectRedisTemplate<?> getInstance() {
        return ObjectRedisTemplateInner.redisTemplate;
    }

    private static class ObjectRedisTemplateInner {

        private static ObjectRedisTemplate<?> redisTemplate = getObjectRedisTemplate();

        private static ObjectRedisTemplate getObjectRedisTemplate() {
            try {
                // redis 连接配置
                final Environment environment = SpringBeanUtils.getBean(Environment.class);
                String hostName = environment.getRequiredProperty("redis.hostName");
                int port = environment.getProperty("redis.port", Integer.class, 6379);
                String password = environment.getProperty("redis.password", "");
                int database = environment.getProperty("redis.database", Integer.class, 0);
                log.info("redis hostName : {}, port : {}, password : {}, database : {}", hostName, port, password, database);
                if (StringUtils.isAnyEmpty(hostName, password)) {
                    log.info("连接不可用");
                    return null;
                }
                // redis 配置
                // 最大连接数
                int maxToTal = environment.getProperty("redis.maxToTal", Integer.class, 0);
                // 最大空闲数
                int maxIdle = environment.getProperty("redis.maxIdle", Integer.class, 0);
                // 最小空闲数
                int minIdle = environment.getProperty("redis.minIdle", Integer.class, 0);
                // 当池内没有可用的连接时，最大等待时间
                int maxWaitMillis = environment.getProperty("redis.maxWaitMillis", Integer.class, 10);
                // 最小空闲数
                boolean testOnBorrow = environment.getProperty("redis.testOnBorrow", Boolean.class, Boolean.TRUE);

                // 单机配置
                final RedisStandaloneConfiguration redisStandaloneConfiguration = geneRedisStandaloneConfiguration(hostName, port, password, database);

                // 连接池配置
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxTotal(maxToTal);
                jedisPoolConfig.setMinIdle(minIdle);
                jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
                jedisPoolConfig.setMaxIdle(maxIdle);
                jedisPoolConfig.setTestOnBorrow(testOnBorrow);
                final JedisClientConfiguration jedisClientConfiguration = geneJedisClientConfiguration(jedisPoolConfig);

                //            生成单线程配置信息
                //            final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
                // 生成多线程redis连接池客户端配置
                final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);

                return new ObjectRedisTemplate<>(jedisConnectionFactory);
            } catch (Exception e) {
                log.error("redis 连接失败", e);
            }
            return null;
        }

    }

}
