package cn.cpf.web.boot.controller;

import cn.cpf.web.boot.constant.PageTree;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <b>Description : </b> 根 Controller
 * <p>
 * <b>created in </b> 2021/12/27
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@Log4j2
@Controller
public class BootController {

    @Autowired
    private Producer captchaProducer;

    @PostConstruct
    public void start() throws IOException {
        log.debug("{} init", getClass().getSimpleName());
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return PageTree.Base.INDEX;
    }

    @GetMapping("/login")
    public String login() {
        return PageTree.Base.LOGIN;
    }

    @GetMapping("/503")
    public String p503() {
        return PageTree.Base.P503;
    }

    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

}
