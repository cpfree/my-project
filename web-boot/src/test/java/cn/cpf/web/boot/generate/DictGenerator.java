package cn.cpf.web.boot.generate;

import cn.cpf.mod.plugins.velocity.TableDataHandler;
import cn.cpf.web.base.model.bo.SysFieldBo;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.entity.SysFieldExtend;
import cn.cpf.web.boot.WebBootApplication;
import cn.cpf.web.dal.combine.SysDesignCombineMapper;
import cn.cpf.web.service.base.api.ISysDictItem;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/2 17:35
 **/
@SpringBootTest(classes = WebBootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
class DictGenerator {

    private static final String HELLO_WORLD_VM_PATH = "mod/plugins/src/main/resources/vm-template/dict-enum.vm";

    @Autowired
    private ISysDictItem iSysDictItem;
    @Resource
    private SysDesignCombineMapper designCombineMapper;

    @Test
    void contextLoads() {
        final String[] tableArr = {"sys_dict_item"};
        for (String table : tableArr) {
//        final List<SysDictItem> sysDictItemList = iSysDictItem.selectByExample(new SysDictItemExample());
//        System.out.println(sysDictItemList);
            final SysTableBo sysTableBo = designCombineMapper.selectSysTableBoByTableName(table);
            final List<SysFieldBo> sysFieldBos = designCombineMapper.selectSysFieldBoByTableName(table);
            final Set<String> dictTypeSet = sysFieldBos.stream().map(SysFieldExtend::getDictType).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
            final Map<String, List<SysDictItem>> stringListMap = iSysDictItem.selectByDictType(dictTypeSet);


            final TableDataHandler tableDataHandler = new TableDataHandler();
            tableDataHandler.setSysTable(sysTableBo);
            tableDataHandler.setSysFieldList(sysFieldBos);
            tableDataHandler.setDictItemListMap(stringListMap);
            System.out.println("call dict vm generator");
            cn.cpf.mod.plugins.dict.DictGenerator.geneVm(tableDataHandler);

        }

    }


}
