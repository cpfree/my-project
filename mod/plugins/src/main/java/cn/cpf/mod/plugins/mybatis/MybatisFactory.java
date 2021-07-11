package cn.cpf.mod.plugins.mybatis;

import cn.cpf.mod.plugins.dao.DbDaoMapper;
import com.github.cpfniliu.common.ext.bean.Record;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/11/11 19:22
 **/
public class MybatisFactory {

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getInstance(){
        return sqlSessionFactory.openSession();
    }

    public static SqlSession getBatchInstance(){
        return sqlSessionFactory.openSession(ExecutorType.BATCH);
    }

    public static void main(String[] args) {
        final SqlSession instance = MybatisFactory.getInstance();
        final DbDaoMapper mapper = instance.getMapper(DbDaoMapper.class);
        final List<Record> list = mapper.selectDbInfo("sys_table");
        System.out.println(list);
        instance.commit();
        instance.close();
    }

}
