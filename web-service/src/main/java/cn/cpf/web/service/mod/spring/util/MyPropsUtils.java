package cn.cpf.web.service.mod.spring.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/16 11:23
 **/
@Slf4j
public class MyPropsUtils {

    public static Properties getPropsLoader(String location) {
        try {
            return PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource(location), StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error("加载资源失败 : {}", location);
        }
        return new Properties();
    }


}
