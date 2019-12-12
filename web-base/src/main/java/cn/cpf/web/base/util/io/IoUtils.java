package cn.cpf.web.base.util.io;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

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

    /**
     * 读取文件
     *
     * @param filePath 文件路径
     *
     * @return 文件字符串
     */
    public static String readFile(String filePath) {
        final File file = new File(filePath);
        Validate.isTrue(file.exists(), "文件不存在");
        final long length = file.length();
        char[] chars = new char[(int)length];
        try (final FileReader reader = new FileReader(file)){
            final int read = reader.read(chars);
            log.info("read success length " + read);
        } catch (IOException e) {
            log.error("读取文件失败", e);
        }
        return new String(chars);
    }

    /**
     * 往 savePath 路径 写入文件, 如果没有则新增
     *
     * @param savePath 写入路径
     * @param content 内容
     *
     */
    public static void writeFile(@NonNull String savePath, @NonNull String content) {
        final File file = new File(savePath);
        // 创建文件夹(如果没有)
        final String dirPath = file.getParent();
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory()) {
            final boolean mkDirs = dir.mkdirs();
            Validate.isTrue(mkDirs, "文件夹创建失败");
            log.info("文件夹创建成功: {} ", dirPath);
        }
        Validate.isTrue(dir.isDirectory(), "创建文件夹异常, 已存在同名非文件夹事物, 请检查 path : " + dir.getPath());

        // 创建文件
        final boolean exists = file.exists();
        if (!exists) {
            try {
                final boolean newFile = file.createNewFile();
                Validate.isTrue(newFile, "文件创建失败");
            } catch (IOException e) {
                log.error("文件创建失败", e);
            }
        }
        Validate.isTrue(file.isFile(), "创建文件异常, 已存在同名非文件事物, 请检查 path : " + file.getPath());

        // 写入文件
        try (final FileWriter writer = new FileWriter(file)){
            writer.write(content);
        } catch (IOException e) {
            log.error("读取文件失败", e);
        }
    }

}
