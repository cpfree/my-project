package cn.cpf.mod.plugins.dao;

import cn.cpf.web.base.lang.base.Record;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/3 13:22
 **/
public interface DbDaoMapper {

    @Select("select column_name, data_type, column_comment from information_schema.COLUMNS WHERE TABLE_SCHEMA = 'my-project' and TABLE_NAME = #{tableName}")
    List<Record> selectDbInfo(@Param("tableName") String tableName);

}
