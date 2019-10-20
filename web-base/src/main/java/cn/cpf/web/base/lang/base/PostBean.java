package cn.cpf.web.base.lang.base;

import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <b>Description : </b> 用来处理后端向前端的数据传递
 *
 * @author CPF
 * @date 2019/3/12 10:22
 **/
public class PostBean extends RecordMap {

    private IPostCode returnCode = null;

    public PostBean(IPostCode returnCode) {
        this.returnCode = returnCode;
    }

    public PostBean() {
    }

    public IPostCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(IPostCode returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 如果 returnCode 为空, 则设置值
     */
    public void setReturnCodeIfNull(IPostCode returnCode) {
        if (this.returnCode == null) {
            this.returnCode = returnCode;
        }
    }

    /**
     * 是否不成功, 实际判断的是 returnCode 是否是错误代码
     */
    public boolean isNotSuccess() {
        return returnCode != null && returnCode.isNotSuccess();
    }

    public Map<String, Object> toResultMap() {
        if (returnCode == null) {
            returnCode = ECommonPostCode.SUCCESS;
        }
        Map<String, Object> result = genePostMap(returnCode);
        if (map != null) {
            result.put("data", map);
        }
        return result;
    }

    /**
     * 该函数稍微破坏前后端数据传送规则, 小心使用
     *
     * @param object
     * @return
     */
    public static Map<String, Object> geneSimpleDataPostMap(Object object) {
        Map<String, Object> defaultMap = genePostMap(ECommonPostCode.SUCCESS);
        if (object != null) {
            defaultMap.put("data", object);
        }
        return defaultMap;
    }

    public static Map<String, Object> genePostMap() {
        return genePostMap(ECommonPostCode.SUCCESS);
    }

    public static Map<String, Object> genePostMap(IPostCode postCode) {
        Map<String, Object> status = Maps.newHashMapWithExpectedSize(3);
        status.put("code", postCode.getCode());
        status.put("text", postCode.getText());
        status.put("desc", postCode.getDesc());
        Map<String, Object> result = Maps.newHashMapWithExpectedSize(2);
        result.put("status", status);
        return result;
    }

}
