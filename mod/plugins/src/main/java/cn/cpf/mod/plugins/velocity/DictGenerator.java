package cn.cpf.mod.plugins.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/2 17:35
 **/
public class DictGenerator {

    private static final String HELLO_WORLD_VM_PATH = "mod/plugins/src/main/resources/template/userInfo.vm";

    public static void main(String[] args) {
        sayHelloFromVM(HELLO_WORLD_VM_PATH);
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
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        System.out.println(writer.toString());
    }


}
