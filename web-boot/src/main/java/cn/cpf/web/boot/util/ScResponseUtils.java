package cn.cpf.web.boot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <b>Description : </b>
 * <p>
 * <b>created in </b> 2022/1/1
 * </p>
 *
 * @author CPF
 * @since 1.0
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScResponseUtils {

    public static void writeApplicationJson(ServletResponse response, String content) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(content);
    }

    public static void writeImage(HttpServletResponse response, BufferedImage bufferedImage) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();
        }
    }

}
