package cn.cpf.web.service.mod.redis.base;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/9/23 17:48
 **/
public class ObjectRedisTemplate<T> extends RedisTemplate<String, T> {

    private ObjectRedisTemplate() {
        RedisSerializer<Object> jsonRedisSerializer = RedisSerializer.json();
        this.setKeySerializer(new StringRedisSerializer());
        this.setValueSerializer(jsonRedisSerializer);
        this.setHashKeySerializer(jsonRedisSerializer);
        this.setHashValueSerializer(jsonRedisSerializer);
    }

    public ObjectRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

}
