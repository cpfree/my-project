package cn.cpf.web.base.util.io;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

/**
 *
 */
@Slf4j
public final class PropsUtil {

    public static Map<String, Object> cfgMap;


    /**
     * 加载文件
     *
     * @param filename 资源文件路径及文件名
     */
    public static Properties loadProps(String filename) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            if (is == null) {
                throw new FileNotFoundException(filename + " file is not found");
            }
            props = new Properties();
            try (final InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8)){
                props.load(inputStreamReader);
            }
        } catch (IOException e) {
            log.error(filename + " file load failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("close input stream failure! ## relational filename : " + filename, e);
                }
            }
        }
        return props;
    }

    /**
     * 获取属性
     *
     * @param filename     properties文件名
     * @param key          键值
     * @param defaultValue 获取失败的默认值
     */
    public static String getString(String filename, String key, String defaultValue) {
        return loadProps(filename).getProperty(key, defaultValue);
    }

    /**
     * 获取属性
     *
     * @param filename properties文件名
     * @param key      键值
     */
    public static String getString(String filename, String key) {
        return loadProps(filename).getProperty(key);
    }


    /**
     * 获取属性
     *
     * @param filename     properties文件名
     * @param key          键值
     * @param defaultValue 获取失败的默认值
     */
    public static String getInt(String filename, String key, String defaultValue) {
        return loadProps(filename).getProperty(key, defaultValue);
    }

    /**
     * 获取属性
     *
     * @param filename properties文件名
     * @param key      键值
     */
    public static String getInt(String filename, String key) {
        return loadProps(filename).getProperty(key);
    }

    public static void main(String[] args) {
        String str = getString("jdbc", "furl");
        System.out.println(str);
    }

}
