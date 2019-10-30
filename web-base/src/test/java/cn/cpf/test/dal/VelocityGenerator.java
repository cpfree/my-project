package cn.cpf.test.dal;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileWriter;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/25 11:07
 **/
public class VelocityGenerator {

    // 定义一个开关flag=false，不覆盖
    private final static boolean flag = !true;
    // 1.那些domain需要生成代码
    private String[] domains = {"Address"};
    // 2.定义固定的目录路径:都是使用相对路径,规范：路径前面都不加/,路径的后面都加/
    private final static String SRC = "src/";
    private final static String PACKAGE = "cn/Sirius_MK/admk/";
    private final static String WEBAPP = "webapp/";
    //获取首字母小写的实体用于创建包
    private String lowerEntity = domains[0].substring(0, 1).toLowerCase() + domains[0].substring(1);
    // 3.有那些模板需要生成
    private String[] templates = {"ServiceImpl.java", "Service.java", "Controller.java", "entity.jsp", "input.js", "input.jsp", "Repository.java"};
    // 4.模板文件对应的生成文件路径
    private String[] files = {SRC + PACKAGE + "service/impl/", SRC + PACKAGE + "service/", SRC + PACKAGE + "controller/", WEBAPP + "WEB-INF/views/pages/" + lowerEntity + "/", WEBAPP + "static/js/" + lowerEntity + "/", WEBAPP + "WEB-INF/views/pages/" + lowerEntity + "/", SRC + PACKAGE + "repository/"};

    public void create() throws Exception {
        if (templates.length != files.length) {
            throw new RuntimeException("templates.length != files.length");
        }

        // 实例化Velocity上下文
        VelocityContext context = new VelocityContext();
        // 5.外循环：domains
        for (int i = 0; i < domains.length; i++) {
            context.put("entity", domains[i]);
            // 定义domain的首字母小写
            // 6.处理domain首字母小写
            String lowerCaseEntity = domains[i].substring(0, 1).toLowerCase() + domains[i].substring(1);
            context.put("lowerCaseEntity", lowerCaseEntity);
            // 7.内循环：templates和files
            for (int j = 0; j < templates.length; j++) {
                // 8.实例化文件存放的路径
                File file = new File(files[j] + domains[i] + templates[j]);
                // 9.处理特殊的文件名称
                if ("Service.java".equals(templates[j])) {
                    file = new File(files[j] + "I" + domains[i] + templates[j]);
                } else if ("entity.jsp".equals(templates[j])) {
                    file = new File(files[j] + lowerCaseEntity + ".jsp");
                } else if ("input.js".equals(templates[j])) {
                    file = new File(files[j] + lowerCaseEntity + "_" + templates[j]);
                } else if ("input.jsp".equals(templates[j])) {
                    file = new File(files[j] + lowerCaseEntity + "_" + templates[j]);
                }
                // 12.开关：默认情况下已经存在的文件不需要生成代码 true:覆盖所有代码
                // 如果flag==false并且当前生存文件是存在的
                if (!flag && file.exists()) {
                    // return;
                    // break;
                    continue;// 本次跳过，继续下一次循环，
                }

                // 10.判断父目录是否存在
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                System.out.println(file.getAbsolutePath());

                // 获取模版
                Template template = Velocity.getTemplate("webapp/template/" + templates[j], "UTF-8");
                try (FileWriter writer = new FileWriter(file)){
                    template.merge(context, writer);
                }
            }
        }
        System.out.println("先刷新工程,修改对应表需要展示的字段,修改需要编辑保存的字段,运行测试");
        System.out.println("最后注意修改主菜单的地址运行测试");
    }

}
