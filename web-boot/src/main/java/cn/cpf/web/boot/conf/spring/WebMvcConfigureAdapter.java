//package cn.cpf.web.boot.conf.spring;
//
//import cn.cpf.web.boot.conf.filter.ScActionInterceptor;
//import cn.cpf.web.boot.conf.filter.ScFilter;
//import cn.cpf.web.boot.conf.filter.ScHttpSessionListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * <b>Description : </b>
// * <p>
// * <b>created in </b> 2021/12/29
// * </p>
// *
// * @author CPF
// * @since 1.0
// **/
//@Configuration
//public class WebMvcConfigureAdapter implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
////        registry.addInterceptor(scActionInterceptor()).addPathPatterns("/**");
//    }
//
//    @Bean
//    public ScHttpSessionListener scHttpSessionListener() {
//        return new ScHttpSessionListener();
//    }
//
//    @Bean
//    public ScFilter scFilter() {
//        return new ScFilter();
//    }
//
//    @Bean
//    public ScActionInterceptor scActionInterceptor() {
//        return new ScActionInterceptor();
//    }
//}