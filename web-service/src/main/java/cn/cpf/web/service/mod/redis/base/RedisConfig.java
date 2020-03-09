package cn.cpf.web.service.mod.redis.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/11 11:24
 **/
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "redis")
@Component
@Data
public class RedisConfig {

    /**
     * redis服务器的host或者ip地址
     */
    private String hostName;
    /**
     * 服务的端口号
     */
    private int port;
    /**
     * 默认使用的数据库
     */
    private int database;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接池 : 最大连接数
     */
    private int maxToTal;
    /**
     * 连接池 : 最大空闲数
     */
    private int maxIdle;
    /**
     * 连接池 : 最小空闲数
     */
    private int minIdle;
    /**
     * 连接池 : 当池内没有可用的连接时，最大等待时间
     */
    private int maxWaitMillis;
    /**
     * 使用连接时是否测试可用
     */
    private boolean testOnBorrow;

}
