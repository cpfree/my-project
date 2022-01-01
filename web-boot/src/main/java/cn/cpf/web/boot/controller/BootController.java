package cn.cpf.web.boot.controller;

import cn.cpf.web.base.lang.base.IPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.util.cast.JsonUtils;
import cn.cpf.web.boot.constant.PageTree;
import cn.cpf.web.boot.constant.PostKeyConst;
import cn.cpf.web.boot.util.CpRequestUtils;
import cn.cpf.web.boot.util.ScResponseUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

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

    /**
     * 执行 POST 登录后无论登录成功还是失败, 只要不报系统错误, 都会进入该方法.
     */
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PostBean post = new PostBean();
        final Boolean needCaptcha = CpRequestUtils.isNeedCaptcha(request);
        if (Boolean.TRUE.equals(needCaptcha)) {
            post.put(PostKeyConst.NEED_CAPTCHA, true);
        }
        final IPostCode postCode = CpRequestUtils.getPostCode(request);
        if (postCode != null) {
            post.setReturnCode(postCode);
        }
        // 如果有错误向前台返回错误
        if (postCode != null && postCode.isNotSuccess()) {
            post.put(PostKeyConst.NEED_CAPTCHA, true);
            ScResponseUtils.writeApplicationJson(response, JsonUtils.toJson(post.toResultMap()));
            return Collections.emptyMap();
        }
        return post.toResultMap();
    }

    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ScResponseUtils.writeImage(response, bi);
    }

}
