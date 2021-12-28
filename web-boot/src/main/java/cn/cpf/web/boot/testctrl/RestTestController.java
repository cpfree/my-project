package cn.cpf.web.boot.testctrl;

import cn.cpf.web.boot.conf.RocketConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description : </b> 用于测试的 Controller, 其中的方法与业务无关
 * <p>
 * <b>created in </b> 2019/8/15 10:07
 * </p>
 *
 * @author CPF
 **/
@Log4j2
@RestController
public class RestTestController {

    @Autowired
    RocketConfig rocketConfig;

    @PostConstruct
    public void start() {
        log.info(rocketConfig);
    }

    /**
     * 测试 仅仅将 shortName 简单处理后返回
     */
    @RequestMapping("/navigationStatic/{shortName}")
    public String navigationStatic(HttpServletRequest request, HttpSession session, @PathVariable String shortName) {
        return shortName.replace("-", "/");
    }

    @RequestMapping("/redirect/{shortName}")
    public String redirect(HttpServletRequest request, HttpSession session, @PathVariable String shortName) {
        return "redirect:/" + shortName.replace("-", "/");
    }

    @RequestMapping("/hello")
    public String testHello() {
        log.info(rocketConfig);
        return "Hello World";
    }

    @GetMapping("/testMap")
    public Map<String, String> testGetMap() {
        Map<String, String> map = new HashMap<>();
        map.put("get", "测试testGetMap");
        map.put("function", "测试testGetMap 方法");
        log.info(rocketConfig);
        return map;
    }

    @PostMapping("/testMap")
    public Map<String, String> testPostMap() {
        Map<String, String> map = new HashMap<>();
        map.put("post", "测试PostMapping");
        map.put("function", "测试PostMapping 方法");
        log.info(rocketConfig);
        return map;
    }

}
