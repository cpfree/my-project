package cn.cpf.web.boot.conf;

import cn.cpf.web.boot.conf.filter.MyFilter;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/15 10:36
 **/
@Configuration
@Log4j2
public class WebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }
}