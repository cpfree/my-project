package cn.cpf.web.boot.controller;

import cn.cpf.web.boot.conf.RocketConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/15 10:07
 **/
@RestController
@Log4j2
public class TestController {

    @Autowired
    RocketConfig rocketConfig;

    @PostConstruct
    public void start() {
        log.info(rocketConfig);
        System.out.println(rocketConfig.toString());
    }

    @RequestMapping("/navigationStatic/{shortName}")
    public String navigationStatic(HttpServletRequest request, HttpSession session, @PathVariable String shortName) {
        return shortName.replace("-", "/");
    }
    @RequestMapping("/redirect/{shortName}")
    public String redirect(HttpServletRequest request, HttpSession session, @PathVariable String shortName) {
        return "redirect:/" + shortName.replace("-", "/");
    }

    @RequestMapping({"/", "/index"})
    public String index() {
        return "Hello world";
    }

    @RequestMapping("/hello")
    public String indefdfx() {
        log.info(rocketConfig);
        return "Hello World";
    }

    @RequestMapping("/hellomap")
    public Map<String, String> index1() {
        Map<String, String> map = new HashMap<>();
        map.put("aj", "fd");
        map.put("jfd", "fdj");
        log.info(rocketConfig);
        return map;
    }

}
