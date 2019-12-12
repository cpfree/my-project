package cn.cpf.mod.plugins.velocity;

import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <b>Description : </b> 用于生成模板文件
 *
 * @author CPF
 * @date 2019/12/3 16:50
 **/
@Data
@Builder
public class TableDataHandler {

    /**
     * 表数据信息
     */
    private SysTableBo sysTable;
    /**
     * 表字段信息
     */
    private List<SysFieldBo> sysFieldList;
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
