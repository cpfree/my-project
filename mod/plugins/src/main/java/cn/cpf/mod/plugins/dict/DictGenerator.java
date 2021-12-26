package cn.cpf.mod.plugins.dict;

import cn.cpf.mod.plugins.velocity.TableDataHandler;
import cn.cpf.mod.plugins.velocity.VelocityGeneInfoBean;
import cn.cpf.mod.plugins.velocity.VelocityUtils;
import cn.cpf.web.base.model.bo.SysTableBo;
import cn.cpf.web.base.model.entity.SysDictItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cosycode.common.ext.bean.Record;
import com.google.gson.Gson;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/9 19:09
 **/
public class DictGenerator {

    private static final String HELLO_WORLD_VM_PATH = "P:/git/my-project/mod/plugins/src/main/resources/template/dict.vm";

    private static final String SAVE_PATH = "P:\\git\\my-project\\mod\\plugins\\src\\main\\resources\\templateSave\\test.java";

    public static TableDataHandler getInstance() throws IOException {
        final String s = "{\"sysTable\":{\"schemaTag\":\"my-project\",\"name\":\"sys_dict_item\",\"simpleName\":\"\",\"type\":\"sys\",\"comment\":\"系统字典项表\"},\"sysFieldList\":[{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"type\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":1,\"comment\":\"\",\"ordinalPosition\":1,\"columnDefault\":null,\"isNullable\":\"NO\",\"dataType\":\"varchar\",\"characterMaximumLength\":50,\"columnType\":\"varchar(50)\",\"columnKey\":\"PRI\",\"columnComment\":\"字典类别\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"name\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":2,\"comment\":\"\",\"ordinalPosition\":2,\"columnDefault\":null,\"isNullable\":\"NO\",\"dataType\":\"varchar\",\"characterMaximumLength\":50,\"columnType\":\"varchar(50)\",\"columnKey\":\"PRI\",\"columnComment\":\"枚举的name, 作为字典项的识别标志, 在代码里面作为枚举标识使用, 无论字典如何配置, name不变, 一个枚举类型里面name唯一\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"value\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":3,\"comment\":\"\",\"ordinalPosition\":3,\"columnDefault\":null,\"isNullable\":\"NO\",\"dataType\":\"varchar\",\"characterMaximumLength\":20,\"columnType\":\"varchar(20)\",\"columnKey\":\"\",\"columnComment\":\"值\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"cn_label\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":4,\"comment\":\"\",\"ordinalPosition\":4,\"columnDefault\":null,\"isNullable\":\"NO\",\"dataType\":\"varchar\",\"characterMaximumLength\":50,\"columnType\":\"varchar(50)\",\"columnKey\":\"\",\"columnComment\":\"中文标签\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"en_label\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":5,\"comment\":\"\",\"ordinalPosition\":5,\"columnDefault\":null,\"isNullable\":\"NO\",\"dataType\":\"varchar\",\"characterMaximumLength\":50,\"columnType\":\"varchar(50)\",\"columnKey\":\"\",\"columnComment\":\"英文标签\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"par_value\",\"label\":null,\"dictType\":null,\"showSearch\":null,\"showList\":null,\"showAdd\":null,\"showEdit\":null,\"showDetail\":null,\"sort\":null,\"comment\":null,\"ordinalPosition\":6,\"columnDefault\":\"\",\"isNullable\":\"NO\",\"dataType\":\"varchar\",\"characterMaximumLength\":12,\"columnType\":\"varchar(12)\",\"columnKey\":\"\",\"columnComment\":\"父ID, 多级字典表时有用\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"level\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":7,\"comment\":\"\",\"ordinalPosition\":7,\"columnDefault\":\"1\",\"isNullable\":\"NO\",\"dataType\":\"int\",\"characterMaximumLength\":0,\"columnType\":\"int(1)\",\"columnKey\":\"\",\"columnComment\":\"层级, 当前字典项的层级, 多级字典表时有用\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"ord\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":8,\"comment\":\"\",\"ordinalPosition\":8,\"columnDefault\":\"10\",\"isNullable\":\"NO\",\"dataType\":\"int\",\"characterMaximumLength\":0,\"columnType\":\"int(3)\",\"columnKey\":\"\",\"columnComment\":\"顺序, 字典项显示的顺序\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"comment\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":9,\"comment\":\"\",\"ordinalPosition\":9,\"columnDefault\":\"\",\"isNullable\":\"NO\",\"dataType\":\"varchar\",\"characterMaximumLength\":400,\"columnType\":\"varchar(400)\",\"columnKey\":\"\",\"columnComment\":\"数据描述\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"permission\",\"label\":\"\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":10,\"comment\":\"\",\"ordinalPosition\":10,\"columnDefault\":\"\",\"isNullable\":\"NO\",\"dataType\":\"char\",\"characterMaximumLength\":2,\"columnType\":\"char(2)\",\"columnKey\":\"\",\"columnComment\":\"权限规则{删, 改值}\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"add_time\",\"label\":\"添加时间\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":11,\"comment\":\"\",\"ordinalPosition\":11,\"columnDefault\":\"CURRENT_TIMESTAMP\",\"isNullable\":\"NO\",\"dataType\":\"datetime\",\"characterMaximumLength\":0,\"columnType\":\"datetime\",\"columnKey\":\"\",\"columnComment\":\"添加时间\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"update_time\",\"label\":\"更新时间\",\"dictType\":\"\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":12,\"comment\":\"\",\"ordinalPosition\":12,\"columnDefault\":\"CURRENT_TIMESTAMP\",\"isNullable\":\"NO\",\"dataType\":\"datetime\",\"characterMaximumLength\":0,\"columnType\":\"datetime\",\"columnKey\":\"\",\"columnComment\":\"更新时间\",\"sysDictType\":null,\"sysDictItemList\":null},{\"schemaTag\":\"my-project\",\"tableName\":\"sys_dict_item\",\"name\":\"state\",\"label\":\"状态\",\"dictType\":\"table_state\",\"showSearch\":\"\",\"showList\":\"\",\"showAdd\":\"\",\"showEdit\":\"\",\"showDetail\":\"\",\"sort\":13,\"comment\":\"\",\"ordinalPosition\":13,\"columnDefault\":null,\"isNullable\":\"NO\",\"dataType\":\"char\",\"characterMaximumLength\":1,\"columnType\":\"char(1)\",\"columnKey\":\"\",\"columnComment\":\"y正常,n删除\",\"sysDictType\":null,\"sysDictItemList\":null}],\"dictItemListMap\":{\"table_state\":[{\"type\":\"table_state\",\"name\":\"disable\",\"value\":\"n\",\"cnLabel\":\"禁用\",\"enLabel\":\"disable\",\"parValue\":\"\",\"level\":1,\"ord\":10,\"comment\":\"\",\"permission\":\"11\",\"addTime\":1576131665000,\"updateTime\":1576131665000,\"state\":\"y\"},{\"type\":\"table_state\",\"name\":\"enable\",\"value\":\"y\",\"cnLabel\":\"可用\",\"enLabel\":\"enable\",\"parValue\":\"\",\"level\":1,\"ord\":10,\"comment\":\"\",\"permission\":\"11\",\"addTime\":1576131665000,\"updateTime\":1576131665000,\"state\":\"y\"}]},\"modalInfoList\":null}";
        return new ObjectMapper().readValue(s, TableDataHandler.class);
    }


    public static void main(String[] args) throws IOException {
        geneVm(getInstance());
    }

    public static void geneVm(TableDataHandler instance) throws IOException {
        final DictGeneBean dictGeneBean = new DictGeneBean(instance);
        if (CollectionUtils.isEmpty(dictGeneBean.getList())) {
            return;
        }

        final String json = new Gson().toJson(dictGeneBean);
        System.out.println(json);
        Record record = new Record();

        final Record dataBean = new Gson().fromJson(json, Record.class);
        record.put("dataBean", dataBean);
        final VelocityGeneInfoBean build = VelocityGeneInfoBean.builder().savePath(SAVE_PATH).vmPath(HELLO_WORLD_VM_PATH).build();
        VelocityUtils.generate(build, record);
    }



    /**
     * <b>Description : </b>
     *
     * @author CPF
     * @date 2019/12/13 9:33
     **/
    @Data
    static class DictGeneBean {

        public DictGeneBean(TableDataHandler handler) {
            if (handler == null) {
                return;
            }
            final SysTableBo sysTable = handler.getSysTable();
            final Map<String, List<SysDictItem>> dictItemListMap = handler.getDictItemListMap();
            this.setComment(sysTable.getComment());
            this.setTableName(sysTable.getName());
            final List<VmFiledBean> vmFiledBeanList = handler.getSysFieldList().stream().filter(it -> StringUtils.isNotBlank(it.getDictType())).map(sysFieldBo -> {
                // create
                VmFiledBean filedBean = new VmFiledBean();
                filedBean.setName(sysFieldBo.getName());
                filedBean.setComment(StringUtils.isNotBlank(sysFieldBo.getComment()) ? sysFieldBo.getComment() : sysFieldBo.getColumnComment());
                final List<SysDictItem> sysDictItemList = dictItemListMap.get(sysFieldBo.getDictType());
                if (CollectionUtils.isNotEmpty(sysDictItemList)) {
                    final List<VmDictBean> vmDictBeanList = sysDictItemList.stream().map(it -> {
                        final VmDictBean vmDictBean = new VmDictBean();
                        vmDictBean.setName(it.getName());
                        vmDictBean.setValue(it.getValue());
                        vmDictBean.setCnLabel(it.getCnLabel());
                        vmDictBean.setEnLabel(it.getEnLabel());
                        vmDictBean.setComment(it.getComment());
                        return vmDictBean;
                    }).collect(Collectors.toList());
                    filedBean.setList(vmDictBeanList);
                }
                return filedBean;
            }).collect(Collectors.toList());
            this.setList(vmFiledBeanList);
        }

        String tableName;

        String comment;

        List<VmFiledBean> list;

    }

    @Data
    static class VmFiledBean {

        String name;

        String comment;

        List<VmDictBean> list;

    }

    @Data
    static class VmDictBean {

        String name;

        String value;

        String cnLabel;

        String enLabel;

        String comment;

    }

}
