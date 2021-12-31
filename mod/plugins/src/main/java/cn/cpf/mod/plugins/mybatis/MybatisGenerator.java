package cn.cpf.mod.plugins.mybatis;

import com.github.cosycode.common.util.common.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
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
 * <b>Description : </b> mybatis 生成代码
 *
 * @author CPF
 * @date 2019/10/14 17:39
 **/
@Slf4j
public class MybatisGenerator {

    private static final String[] TABLE_NAME_ARR = {"acc_user"};

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        geneStandFile();
    }

    /**
     * 生成正式 Mybatis 文件, 会覆盖 web-boot 上面的文件
     *
     */
    public static void geneStandFile() throws XMLParserException, SQLException, IOException, InterruptedException, InvalidConfigurationException {
        generate(TABLE_NAME_ARR, "generatorConfig.xml", true);
    }

    /**
     * 生成临时文件, 文件生成到 plugin 文件夹下
     *
     */
    public static void geneStandFileTest() throws XMLParserException, SQLException, IOException, InterruptedException, InvalidConfigurationException {
        generate(TABLE_NAME_ARR, "generatorConfig-test.xml", true);
    }

    /**
     *
     * @param tables 生成的数据库表数组
     * @param mybatisGeneConfigPath 资源配置文件路径
     * @param clearConfigFileEntity 是否清空资源配置文件里面的 Entity 配置, 若该项为 true, 则资源文件里面的 Entity 配置无效, 只有 tables 里面的有效.
     */
    public static void generate(String[] tables, String mybatisGeneConfigPath, boolean clearConfigFileEntity) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        //指定 逆向工程配置文件
        final InputStream resourceAsStream = MyBatisGenerator.class.getClassLoader().getResourceAsStream(mybatisGeneConfigPath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(resourceAsStream);
        final Context context = config.getContexts().get(0);
        if (clearConfigFileEntity) {
            // 清空 配置文件里面的 配置
            context.getTableConfigurations().clear();
        }
        // 添加 tables 里面的 entity
        if (tables != null) {
            Arrays.stream(tables).forEach(t -> {
                TableConfiguration configuration = new TableConfiguration(context);
                configuration.setTableName(t);
                configuration.setDomainObjectName(StrUtils.firstCharToUpperCase(StrUtils.lowerCamel(t)));
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
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        log.info("{}", callback.isOverwriteEnabled());
        log.info("{}", warnings);
    }

}
