package cn.cpf.web.boot.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/15 10:56
 **/
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "rocketmq")
@Component
@Data
public class RocketConfig {

    private String useMq;
    private String nameSrvAddr;
    private String producerGroup;
    private String consumerGroup;

}
