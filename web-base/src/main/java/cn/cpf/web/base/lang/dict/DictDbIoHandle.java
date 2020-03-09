package cn.cpf.web.base.lang.dict;

import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/16 14:06
 **/
public abstract class DictDbIoHandle {

    public abstract Map<String, DictTypeBean> queryAllDataFromDb();

    public Map<String, DictTypeBean> queryPartDataFromDb(List<String> fieldKeyList){return null;}

    public abstract DictTypeBean queryOneDataFromDb(String fieldKey);

}
