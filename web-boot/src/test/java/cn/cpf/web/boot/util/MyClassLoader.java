package cn.cpf.web.boot.util;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/6 16:21
 **/
@Slf4j
public class MyClassLoader extends ClassLoader {

    /**
     * classDir记录类所在目录
     */
    private String classDir;

    /**
     * @param classDir	待加载的类所在的目录
     */
    public MyClassLoader(@NonNull String classDir) {
        if (!classDir.endsWith("\\") && !classDir.endsWith("/")) {
            classDir += "/";
        }
        this.classDir = classDir;
    }

    public Class<?> getClass(String name) {
        return findClass(name);
    }

    /**
     * 复写findClass方法
     * 因为自定义的类加载器MyClassLoader继承了ClassLoader类，继承了其所有方法
     * 通过loadClass(String name)方法加载类的时候：
     * 1、会检查该类是否已经加载，如果没有，会依次委托MyClassLoader的父类、父类的父类
     *    等类加载器依次尝试加载，都失败则执行findClass(String name)试图获取待加载的类的Class对象
     * 2、而ClassLoader中findClass(String name)方法直接抛出ClassNotFoundException(name)
     * 3、因此复写findClass(String name)方法就是以自定义的方式获取类（在父类都无法加载的情况）
     * 4、通过将某个.class文件读入一个byte类型的数组中，然后通过defineClass方法
     *    获取该字节码文件（Class对象）并返回
     * 5、因此，当1中的情况出现时，本类即可通过findClass(String name)获取Class对象给
     *    loadClass(String name)方法，使其返回同一个Class对象
     *
     *    本类主函数内：
     *    new MyClassLoader("ClassLoaderLib").loadClass("Person");
     *    new MyClassLoader("ClassLoaderLib").findClass("Person");
     *
     *    因为protected权限修饰符，其他类调用本类加载某个类只能用loadClass方法
     *
     */
    @Override
    protected Class<?> findClass(String name) {
        //获取字节码文件的完整路径
        String classPath = classDir + name.replace(".",File.separator) + ".class";
        //定义Class对象引用
        Class<?> clazz = null;
        //定义字节数组输出流引用
        //设置字节输入流引用
        try (BufferedInputStream bos = new BufferedInputStream(new FileInputStream(classPath))){
            @Cleanup ByteArrayOutputStream baos = null;
            //建立字节输入流
            //定义缓冲数组buf，len记录结束标记
            byte[] buf = new byte[1024];
            int len;
            //新建字节数组输出流（内部维护了一个字节数组）
            baos = new ByteArrayOutputStream();
            //循环读取，写入baos中的字节数组
            while ((len = bos.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            //获取baos中的字节数组
            byte[] bytes = baos.toByteArray();
            //将该字节数组变成字节码文件
            clazz = defineClass(null, bytes, 0, bytes.length);
        } catch (IOException e) {
            log.error("", e);
        }
        return clazz;
    }

}