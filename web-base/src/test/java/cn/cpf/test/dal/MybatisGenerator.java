package cn.cpf.test.dal;

import cn.cpf.web.base.util.common.StrUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/10/14 17:39
 **/
public class MybatisGenerator {


    private static String[] tableNameArr = {};
//    private static String[] tableNameArr = {"acc_user","acc_role","acc_permission","acc_resource","acc_link_user_role","acc_link_role_perm","acc_link_perm_res"};

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        //指定 逆向工程配置文件
        final InputStream resourceAsStream = MyBatisGenerator.class.getClassLoader().getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(resourceAsStream);
        addTable(config.getContexts().get(0));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println(callback.isOverwriteEnabled());
        System.out.println(warnings);
    }


    private static void addTable(final Context context) {
        if (tableNameArr == null) {
            return;
        }
        Arrays.stream(tableNameArr).forEach(t->{
            TableConfiguration configuration = new TableConfiguration(context);
            configuration.setTableName(t);
            configuration.setDomainObjectName(StrUtils.firstCharToUpperCase(StrUtils.camelize(t)));
            configuration.setCountByExampleStatementEnabled(true);
            configuration.setUpdateByExampleStatementEnabled(true);
            configuration.setUpdateByPrimaryKeyStatementEnabled(true);
            configuration.setDeleteByExampleStatementEnabled(true);
            configuration.setDeleteByPrimaryKeyStatementEnabled(true);
            configuration.setSelectByExampleStatementEnabled(true);
            configuration.setSelectByPrimaryKeyStatementEnabled(true);
            context.addTableConfiguration(configuration);
        });
    }

}
