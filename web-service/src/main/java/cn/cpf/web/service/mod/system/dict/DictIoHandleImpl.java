package cn.cpf.web.service.mod.system.dict;

import cn.cpf.web.base.lang.dict.DictDbIoHandle;
import cn.cpf.web.base.lang.dict.DictHandler;
import cn.cpf.web.base.lang.dict.DictTypeBean;
import cn.cpf.web.service.combine.api.ISysDesignCombine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        return iSysDesignCombine.queryDictItem();
    }

    @Override
    public DictTypeBean queryOneDataFromDb(String fieldKey) {
        final Map<String, DictTypeBean> map = iSysDesignCombine.queryDictItem(fieldKey);
        return Optional.ofNullable(map).orElse(new HashMap<>(1)).get(fieldKey);
    }

}
