package cn.cpf.mod.plugins.velocity;

import cn.cpf.web.base.model.entity.SysField;
import cn.cpf.web.base.model.entity.SysTable;
import lombok.Data;

import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/3 16:50
 **/
@Data
public class TableDataHandler {

    /**
     * 表数据信息
     */
    private SysTable sysTable;
    /**
     * 表字段信息
     */
    private List<SysField> sysFieldList;
    /**
     * 模板信息
     */
    private List<ModalInfo> modalInfoList;

    /**
     * 模板类
     */
    @Data
    public static class ModalInfo {
        /**
         * 模板路径
         */
        private String vmPath;
        /**
         * 存储路径(包含文件名)
         */
        private String savePath;
    }

}
