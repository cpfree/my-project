package cn.cpf.web.boot.conf.spring;

import cn.cpf.web.boot.conf.shiro.CpAuthorizingRealm;
import cn.cpf.web.boot.conf.shiro.CpLogoutFilter;
import cn.cpf.web.boot.conf.shiro.LoginAuthenticationFilter;
import cn.cpf.web.boot.conf.shiro.RememberAuthenticationFilter;
import com.google.common.collect.Maps;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/26 17:05
 **/
@Configuration
public class ShiroConfig {

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(){
        // realm
        final CpAuthorizingRealm realm = new CpAuthorizingRealm();
        // ehCache
        final EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        // manager
        final DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setCacheManager(ehCacheManager);
        return manager;
    }


    /**
     *
     * authc:所有url都必须认证通过才可以访问;
     * anon:所有url都都可以匿名访问;
     * user:remember me的可以访问
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        // 未授权界面
        bean.setUnauthorizedUrl("/403");
        // filter
        Map<String, Filter> filters = Maps.newHashMapWithExpectedSize(3);
        filters.put("login", new LoginAuthenticationFilter());
        filters.put("logout", new CpLogoutFilter());
//        filters.put("rememberAuth", new RememberAuthenticationFilter());
        bean.setFilters(filters);
        // setFilterChainDefinitionMap
        Map<String, String> map = Maps.newHashMap();
        map.put("/**", "anon");
//        map.put("/account/**", "anon");
//        map.put("/debug/**", "anon");
//        map.put("/static/**", "anon");
//        map.put("/logout", "logout");
//        map.put("/validate", "login");
//        map.put("/login", "login");
//        map.put("/register", "login");
//        map.put("/**", "rememberAuth");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
//        return new LifecycleBeanPostProcessor();
//    }
//
//
//    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(){
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager());
//        return advisor;
//    }

}
