package cn.cpf.web.boot.conf.inter;

import com.github.cosycode.common.util.otr.PrintTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <b>Description : </b> spring 容易启动后调用
 * <p>
 * <b>created in </b> 2021/12/27
 * </p>
 * <p>
 *     原理请见 {@link SpringApplication#run(java.lang.String...)}
 *      以及 SpringApplication 的私有方法, callRunners(org.springframework.context.ApplicationContext, org.springframework.boot.ApplicationArguments)
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@Slf4j
@Component
public class MyApplicationRunner implements CommandLineRunner {

    @Value("${server.port}")
    private String port;

    @Value("${spring.runner.openIndex}")
    private boolean openIndex;

    @Override
    public void run(String... args) throws Exception {
        log.info("启动首页面 ==> CommandLineRunner");
        if (openIndex) {
            final String command = "cmd /c start http://localhost:{}/";
            final String format = PrintTool.format(command, Optional.ofNullable(port).filter(StringUtils::isNotBlank).orElse("8080"));
            log.info(format);
            Runtime.getRuntime().exec(format);
        }
    }

}
