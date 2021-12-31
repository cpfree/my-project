//package cn.cpf.web.boot.conf.shiro;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.util.Assert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
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
//@Slf4j
//@Component
//public class QuickStart {
//
//    @Autowired
//    private SecurityManager securityManager;
//
//    @Autowired
//    private SimpleService simpleService;
//
//    public void run() {
//
//        // get the current subject
//        Subject subject = SecurityUtils.getSubject();
//
//        // Subject is not authenticated yet
//        Assert.isTrue(!subject.isAuthenticated());
//
//        // login the subject with a username / password
//        UsernamePasswordToken token = new UsernamePasswordToken("joe.coder", "password");
//        subject.login(token);
//
//        // joe.coder has the "user" role
//        subject.checkRole("user");
//
//        // joe.coder does NOT have the admin role
//        Assert.isTrue(!subject.hasRole("admin"));
//
//        // joe.coder has the "read" permission
//        subject.checkPermission("read");
//
//        // current user is allowed to execute this method.
//        simpleService.readRestrictedCall();
//
//        try {
//            // but not this one!
//            simpleService.writeRestrictedCall();
//        }
//        catch (AuthorizationException e) {
//            log.info("Subject was NOT allowed to execute method 'writeRestrictedCall'");
//        }
//
//        // logout
//        subject.logout();
//        Assert.isTrue(!subject.isAuthenticated());
//    }
//
//
//    /**
//     * Sets the static instance of SecurityManager. This is NOT needed for web applications.
//     */
//    @PostConstruct
//    private void initStaticSecurityManager() {
//        SecurityUtils.setSecurityManager(securityManager);
//    }
//}