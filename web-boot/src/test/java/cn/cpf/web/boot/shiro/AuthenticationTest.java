//package cn.cpf.web.boot.shiro;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.mgt.DefaultSecurityManager;
//import org.apache.shiro.realm.text.IniRealm;
//import org.apache.shiro.subject.Subject;
//import org.junit.Test;
//
//import java.util.Arrays;
//
///**
// * <b>Description : </b>
// * <p>
// * <b>created in </b> 2019/10/26 10:57
// * </p>
// *
// * @author CPF
// **/
//public class AuthenticationTest {
//
//    @Test
//    public void authenticationTest(){
//        //1、此处使用Ini配置文件初始化SecurityManager
//        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//        securityManager.setRealm(iniRealm);
//
//        //2、得到SecurityManager实例 并绑定给SecurityUtils
//        SecurityUtils.setSecurityManager(securityManager);
//        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
//        try {
//            //4、登录，即身份验证
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            //5、身份验证失败
//        }
//
//        //执行认证提交
//        boolean isAuthenticated = subject.isAuthenticated();
//
//        //是否认证通过
//        System.out.println("isAuthenticated : " + isAuthenticated);
//        System.out.println("再次执行 subject.isAuthenticated() : " + subject.isAuthenticated());
//        /*===================退出操作之后，测试是否验证通过======================*/
//
//        //6、退出
//        subject.logout();
//
//        System.out.println("退出后再次执行 subject.isAuthenticated() : " + subject.isAuthenticated());
//    }
//
//    @Test
//    public void authorizationTest(){
//        //1、此处使用Ini配置文件初始化SecurityManager
//        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
//        securityManager.setRealm(iniRealm);
//
//        //2、得到SecurityManager实例 并绑定给SecurityUtils
//        SecurityUtils.setSecurityManager(securityManager);
//        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
//        try {
//            //4、登录，即身份验证
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            //5、身份验证失败
//        }
//
//        //执行认证提交
//        boolean isAuthenticated = subject.isAuthenticated();
//
//        //是否认证通过
//        System.out.println("&信息&信&息& : " + isAuthenticated);
//        System.out.println("isAuthenticated : " + isAuthenticated);
//        System.out.println("再次执行 subject.isAuthenticated() : " + subject.isAuthenticated());
//        /*===================退出操作之后，测试是否验证通过======================*/
//
//
//        //基于角色授权，hashRole传入授权标识
//        boolean ishasRole=subject.hasRole("role1");
//        System.out.println("单个角色判断==="+ishasRole);
//
//        //是否拥有多个角色
//        boolean hashAllRole = subject.hasAllRoles(Arrays.asList("role1","role2","role3"));
//        System.out.println("多个角色判断==="+hashAllRole);
//
//        //基于资源授权，isPermitted传入授权标识
//        boolean ishasPermitted =subject.isPermitted("user:create");
//        System.out.println("单个权限判断==="+ishasPermitted);
//
//        boolean isPermittedAll=subject.isPermittedAll("user:create:1","user:update:1");
//        System.out.println("多个角色判断==="+isPermittedAll);
//
//        //6、退出
//        subject.logout();
//
//        System.out.println("退出后再次执行 subject.isAuthenticated() : " + subject.isAuthenticated());
//    }
//}
