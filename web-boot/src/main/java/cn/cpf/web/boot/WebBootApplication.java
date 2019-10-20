package cn.cpf.web.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/20 12:34
 **/
@SpringBootApplication
@MapperScan("cn.cpf.web.boot.dao")
public class WebBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBootApplication.class, args);
    }

}
