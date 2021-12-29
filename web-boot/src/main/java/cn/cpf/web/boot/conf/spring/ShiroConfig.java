package cn.cpf.web.boot.conf.spring;

import cn.cpf.web.boot.conf.shiro.ScAuthorizingRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <b>Description : </b> shiro 的配置文件
 * <p>
 * <br> 最终就是创建出 {@link org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration} 里面的三个 Bean
 * </p>
 *
 * <p>
 * <b>created in </b> 2019/10/26 17:05
 * </p>
 *
 * @author CPF
 **/
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * @return 自定义Realm
     */
    @Bean
    public Realm realm() {
        return new ScAuthorizingRealm();
    }

    /**
     * 若没有 @Bean("shiroFilterFactoryBean") 则当前 Bean 生效
     * 配置安全管理器
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(){
        log.info("创建 securityManager ");
        final DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm());
//        manager.setCacheManager(cacheManager());
        return manager;
    }

    /**
     * shiro 权限过滤器
     *
     * 若没有 @Bean("shiroFilterFactoryBean") 则当前 Bean 生效
     *
     * authc: 所有url都必须认证通过才可以访问;
     * anon: 所有url都都可以匿名访问;
     * user: remember me的可以访问
     *
     * @return 过滤器
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        // DefaultShiroFilterChainDefinition 里面维护了一个 LinkedMap, 是一个顺序 Map, 在查看权限的时候, 前面的优先级比后面的高.
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // 可以匿名访问
        chainDefinition.addPathDefinition("/", "anon");
        chainDefinition.addPathDefinition("/static/**", "anon");
//        // 匹配 filterMap 里面的过滤器类
//        chainDefinition.addPathDefinition("/logout", "logout");
//        // 可以匿名访问
//        chainDefinition.addPathDefinition("/account/loginVerification", "scLogin");
//        chainDefinition.addPathDefinition("/validate", "scLogin");

//        // 要求认证, 且需要 admin 的角色
//        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
//
//        // 要求认证, 且有 document:read 的权限
//        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");


        // 要求用户权限
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    /**
     * 启动缓存 (仅仅需要 创建一个 CacheManager)
     */
//    @Bean
//    protected CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }

}
