package cn.cpf.web.base.util.io;

import com.github.cosycode.common.util.io.IoUtils;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/4/11 16:26
 **/
@Log4j2
public class AttachmentUtil {

    private static final Logger logger = LoggerFactory.getLogger(AttachmentUtil.class);


    public static void writeResponse(@NonNull File file, String downLoadFileName, HttpServletRequest request, HttpServletResponse response) throws IOException {

        logger.debug("downloadFile fileName: {}", file.getName());
        logger.debug("downloadFile filePath: {}", file.getPath());

        String header = request.getHeader("User-Agent").toUpperCase();
        if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
            downLoadFileName = URLEncoder.encode(downLoadFileName, "utf-8");
            downLoadFileName = downLoadFileName.replace("+", "%20");    //IE下载文件名空格变+号问题
        } else {
            downLoadFileName = new String(downLoadFileName.getBytes(), "ISO8859-1");
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");// 设置文件类型
        response.setHeader("Content-Disposition", "attachment; filename=" + downLoadFileName);

        long fileLength = file.length();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Length", String.valueOf(fileLength));
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[2048];
            int bytesRead = -1;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
        } finally {
            IoUtils.close(bis, bos);
        }
    }


    /**
     * 转换文件路径
     * <p>
     * eg : classpath:path -> D:/.../path
     *
     * @param path
     * @return
     */
    public static String resolveClasspathFilePath(@NonNull ClassLoader classLoader, @NonNull String path) {
        // 如果不是utf-8, 则转化
        if (path.startsWith("classpath:")) {
            URL resource = classLoader.getResource("");
            if (resource == null) {
                return "";
            }
            String parent = resource.getPath();
            try {
                parent = URLDecoder.decode(parent, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("filePath encode to UTF-8 转化失败", e);
            }
            path = path.replace("classpath:", parent);
        }
        return path;
    }
}
