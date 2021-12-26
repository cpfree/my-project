package cn.cpf.mod.plugins.velocity;

import com.github.cosycode.common.ext.bean.Record;
import com.github.cosycode.common.util.io.IoUtils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/2 17:35
 **/
@Slf4j
public class VelocityUtils {

    private static final String HELLO_WORLD_VM_PATH = "P:\\git\\my-project\\mod\\plugins\\src\\main\\resources\\template\\userInfo.vm";
    private static final String SAVE_PATH = "P:\\git\\my-project\\mod\\plugins\\src\\main\\resources\\templateSave\\test.java";

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        final Record record = new Record();
        record.put("hello", "Hello world!!");
        List<String> list = new ArrayList<>();
        list.add("par1");
        list.add("par3");
        list.add("par7");
        list.add("par23");
        record.put("list0", list);
        final VelocityGeneInfoBean build = VelocityGeneInfoBean.builder().savePath(SAVE_PATH).vmPath(HELLO_WORLD_VM_PATH).build();
        generate(build, record);
    }


    /**
     * 简单的hello world
     *
     * @param fileVM
     * @throws Exception
     */
    public static void sayHelloFromVM(String fileVM) {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        Template t = ve.getTemplate(fileVM);
        VelocityContext context = new VelocityContext();
        context.put("hello", "Hello world!!");
        List<String> list = new ArrayList<>();
        list.add("par1");
        list.add("par3");
        list.add("par7");
        list.add("par23");
        context.put("list0", list);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        System.out.println(writer.toString());
    }


    public static void generate(@NonNull VelocityGeneInfoBean bean, @NonNull Map<String, Object> map) throws IOException {
        final String tools = "tool";
        Validate.isTrue(!map.containsKey(tools), "map中不能包含 tools");
        final String templateString = IoUtils.readFile((bean.getVmPath()));
        map.put(tools, GlobalTool.class);
        final String content = generate(templateString, map);
        IoUtils.writeFile(bean.getSavePath(), content.getBytes());
    }



    /**
     * 渲染模板
     *
     * @param template 模板字符串
     * @param map      参数集合
     * @return 渲染结果
     */
    private static String generate(String template, Map<String, Object> map) {
        // 每次创建一个新实例，防止velocity缓存宏定义
        VelocityEngine velocityEngine = new VelocityEngine();
        // 创建上下文对象
        VelocityContext velocityContext = new VelocityContext();
        if (map != null) {
            map.forEach(velocityContext::put);
        }
        StringWriter stringWriter = new StringWriter();
        // 设置编码
        try {
            // 生成代码
            velocityEngine.evaluate(velocityContext, stringWriter, "Velocity Code Generate", template);
        } catch (Exception e) {
            // 将异常全部捕获，直接返回，用于写入模板
            StringBuilder builder = new StringBuilder("在生成代码时，模板发生了如下语法错误：\n");
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            builder.append(writer.toString());
            return builder.toString().replace("\r", "");
        }
        // 返回结果
        return stringWriter.toString();
    }

}
