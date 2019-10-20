package cn.cpf.web.base.util.io;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/8/28 10:35
 **/
@Slf4j
public class IoUtils {

    /**
     * 关闭流
     *
     * @param closeableList
     * @return 是否关闭成功
     */
    public static boolean close(Closeable... closeableList) {
        boolean flag = true;
        for (Closeable closeable : closeableList) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                flag = false;
                log.error("ioUtils close stream error1" , e);
            }
        }
        return flag;
    }


    /**
     *
     * @param outputStream 输出流
     * @param string        传入的数据
     */
    public static void writeStringToOutputStream(@NonNull OutputStream outputStream, @NonNull String string) throws IOException {
        writeStringToOutputStream(outputStream, string, null);
    }

    /**
     *
     * @param outputStream 输出流
     * @param string        传入的数据
     * @param charset     编码
     */
    public static void writeStringToOutputStream(@NonNull OutputStream outputStream, @NonNull String string, Charset charset) throws IOException {
        string = string.trim();
        if ("".equals(string)) {
            return;
        }
        // 传输
        try (OutputStreamWriter out = charset == null ?
                new OutputStreamWriter(outputStream) : new OutputStreamWriter(outputStream, charset)) {
            out.write(string);
            out.flush();
        }
    }

    /**
     * 输入流转String
     *
     * @param inputStream 输入流
     * @return 转换的字符串
     * @throws IOException
     */
    public static String readStringFromInputStream(InputStream inputStream) throws IOException {
        return readStringFromInputStream(inputStream, null);
    }

    /**
     * 输入流转String
     *
     * @param inputStream 输入流
     * @param charset     编码
     * @return 转换的字符串
     * @throws IOException
     */
    public static String readStringFromInputStream(InputStream inputStream, Charset charset) throws IOException {
        try (InputStreamReader inputStreamReader = charset == null ?
                new InputStreamReader(inputStream) : new InputStreamReader(inputStream, charset)) {
            //字符缓冲流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //字符串缓冲区
            StringBuilder stringBuilder = new StringBuilder();
            String len;
            //按行读
            while((len=bufferedReader.readLine()) != null){
                //追加到字符串缓冲区存放
                stringBuilder.append(len);
            }
            //将字符串返回
            return stringBuilder.toString();
        }
    }

}
