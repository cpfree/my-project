package cn.cpf.mod.plugins.velocity;

import lombok.Builder;
import lombok.Data;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/12 16:20
 **/
@Data
@Builder
public class VelocityGeneInfoBean {

    /**
     * 模板路径
     */
    private String vmPath;
    /**
     * 存储路径(不包含文件名)
     */
    private String savePath;

}
