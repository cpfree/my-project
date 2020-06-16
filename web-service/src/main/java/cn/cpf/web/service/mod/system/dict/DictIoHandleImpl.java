package cn.cpf.web.service.mod.system.dict;

import cn.cpf.web.service.combine.api.ISysDesignCombine;
import com.github.codedict.dynamic.DictDbIoHandle;
import com.github.codedict.dynamic.DictHandler;
import com.github.codedict.dynamic.DictTypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/16 18:18
 **/
@Service
public class DictIoHandleImpl extends DictDbIoHandle {

    @Autowired
    private ISysDesignCombine iSysDesignCombine;

    @PostConstruct
    public void post() {
        DictHandler.register(this);
    }

    @Override
    public Map<String, DictTypeBean> queryAllDataFromDb() {
        return iSysDesignCombine.queryAllDictItem();
    }

    @Override
    public Map<String, DictTypeBean> queryPartDataFromDb(List<String> fieldKeyList) {
        return iSysDesignCombine.queryDictItemByDictType(fieldKeyList);
    }

    @Override
    public DictTypeBean queryOneDataFromDb(String fieldKey) {
        return iSysDesignCombine.queryOneDictTypeBean(fieldKey);
    }

}
