package cn.cpf.web.boot.conf.spring;

import cn.cpf.web.boot.conf.shiro.ScAuthorizingRealm;
import cn.cpf.web.boot.conf.shiro.ScLoginFilter;
import cn.cpf.web.boot.conf.shiro.ScLogoutFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
        manager.setCacheManager(cacheManager());
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
     * 设置问题
     * 1. 尽量不要设置为 chainDefinition.addPathDefinition("/**", "authc");
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
        chainDefinition.addPathDefinition("/kaptcha", "anon");
        // 注册,验证账号,找回密码等
        chainDefinition.addPathDefinition("/sign/**", "anon");
        // 匹配 filterMap 里面的过滤器类
        chainDefinition.addPathDefinition("/logout", "scLogout");
        // 可以匿名访问
        chainDefinition.addPathDefinition("/login", "scLogin");
        chainDefinition.addPathDefinition("/validate", "scLogin");

        // 要求认证, 且需要 admin 的角色
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");

        // 要求认证, 且有 document:read 的权限
        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");


        // 要求用户权限 尽量不要设置：("/**", "authc"): 这种如果页面访问不到,则会导致返回登陆页面,而不是跳转到404页面.
        chainDefinition.addPathDefinition("/authc/**", "authc");

        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

    /**
     * 启动缓存 (仅仅需要 创建一个 CacheManager)
     */
    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }


    /**
     * <br> 添加一个 FilterRegistrationBean 来取消 Spring 对 ScLoginAuthenticationFilter 的注册
     *
     * <br> 简单来说, 该配置实现了 ScLoginAuthenticationFilter 被 Spring Bean 管理, 但是不会被 Spring 注册为 Filter
     *
     * <br> 如果没有改配置, 则 Spring 会将 ScLoginAuthenticationFilter 注册为一个 Spring 过滤器, 且匹配路径为 /*
     *
     * <p>
     * <br> 1. spring 会自动注册实现 Filter 或 Servlet 的 Bean
     * <br> 2. shiro 里面的过滤器是对 Filter 的实现,
     * <br> 3. 当前项目自定义了一个 scLogin 的 ScLoginAuthenticationFilter 过滤器, 它需要放在 shiro 过滤器注册, 而不是被 Spring 过滤器注册, 因此需要单独禁掉该过滤器的注册.
     * </p>
     *
     * <p>
     *     <b>其它方式</b>
     * <br> 其实还有一种方式, 就是不要让 ScLoginAuthenticationFilter 成为一个 Bean, 也就是取消掉 @Component 注解, 但是如此一来,
     * 就需要单独创建一个 filterMap 的 Bean, 用于 shiro 的配置, 采用 new 的 形势来引入 ScLoginAuthenticationFilter, 相对而言比较复杂, 没有该方式优雅.
     * </p>
     *
     * <p>
     *     <b>注意点</b>
     * <br> 1. 方法名取什么无所谓, 但是要注意参数和返回值需要是 Spring 的 Bean, 且最多只能找到一个符合条件的 Bean
     * </p>
     *
     * @param filter 需要禁止 spring 注册的过滤器
     */
    @Bean
    public FilterRegistrationBean<ScLoginFilter> springConfigForDisabledScFilterRegister(ScLoginFilter filter) {
        FilterRegistrationBean<ScLoginFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<ScLogoutFilter> springConfigForDisabledScLogoutFilter(ScLogoutFilter filter) {
        FilterRegistrationBean<ScLogoutFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

}
