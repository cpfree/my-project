package cn.cpf.web.boot.controller;

import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.EFormPostCode;
import cn.cpf.web.base.constant.string.EncodeString;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.entity.SysDictItemKey;
import cn.cpf.web.base.model.entity.SysDictType;
import cn.cpf.web.base.model.example.SysDictItemExample;
import cn.cpf.web.base.model.example.SysDictTypeExample;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.base.util.sql.PagingUtils;
import cn.cpf.web.service.base.api.ISysDictItem;
import cn.cpf.web.service.base.api.ISysDictType;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * type : sysDictType
 * item : sysDictItem
 *
 * @author CPF
 * @date 2019/12/03 08:30
 **/
@Controller
@RequestMapping("/sys/dict")
@RestController
public class SysDictController {

    private final ISysDictType iSysDictType;
    private final ISysDictItem iSysDictItem;

    public SysDictController(ISysDictType iSysDictType, ISysDictItem iSysDictItem) {
        this.iSysDictType = iSysDictType;
        this.iSysDictItem = iSysDictItem;
    }

    /**
     * 查询 sysDictItem 表分页数据
     *
     * @param pValue                    父值
     * @param value                     值
     * @param pageNumber                页号
     * @param pageSize                  页面大小
     */
    @GetMapping("/item/page-info")
    public Map<String, Object> querySysDictItemPageInfo (HttpServletRequest request,
                                                         @RequestParam(value = "pValue", required = false) String pValue,
                                                         @RequestParam(value = "dictType", required = false) String dictType,
                                                         @RequestParam(value = "value", required = false) String value,
                                                         @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        SysDictItemExample example = new SysDictItemExample();
        SysDictItemExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort desc");
        if (StringUtils.isNotBlank(pValue)) {
            criteria.andPValueEqualTo(pValue);
        }
        if (StringUtils.isNotBlank(dictType)) {
            criteria.andTypeEqualTo(dictType);
        }
        if (StringUtils.isNotBlank(value)) {
            criteria.andValueEqualTo(value);
        }
        PageInfo<SysDictItem> pageInfo = PagingUtils.selectStartPagingInfo(() -> iSysDictItem.selectByExample(example), pageNumber, pageSize);
        // 检查文件参数
        PostBean postBean = new PostBean();
        postBean.put("pageInfo", pageInfo);
        return postBean.toResultMap();
    }

    /**
     * 新增一条 sysDictItem 表数据
     */
    @PostMapping("/type/item")
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysDictItem (@ModelAttribute SysDictItem record) {
        final SysDictItem row = iSysDictItem.insert(record);
        if (row == null) {
            return PostBean.genePostMap(EFormPostCode.insertFailure);
        }
        return PostBean.genePostMap(EFormPostCode.insertSuccess);
    }

    /**
     * 更新 sysDictItem 表数据
     */
    @PutMapping("/type/item")
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> editSysDictItem (HttpServletRequest request, @ModelAttribute SysDictItem record) {
        if (StringUtils.isAnyBlank(record.getEnLabel(), record.getCnLabel(), record.getValue())) {
            return PostBean.genePostMap(ECommonPostCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }
        record.setUpdateTime(new Date());
        final int row = iSysDictItem.updateByPrimaryKey(record);
        if (row > 0) {
            return PostBean.genePostMap();
        }
        return PostBean.genePostMap(ECommonPostCode.EXEC_FAILURE);

    }

    /**
     * 删除 sysDictItem 表数据
     */
    @DeleteMapping("/type/item")
    @ResponseBody
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> removeSysDictItem (@RequestParam("guidArr[]") String[] guidArr) {
        try {
            Arrays.stream(guidArr).map(it->{
                final String[] split = it.split("-");
                if (split.length != 2) {
                    throw new RuntimeException("ds");
                }
                return new SysDictItemKey(split[0], split[1]);
            }).forEach(iSysDictItem::deleteByPrimaryKey);
        } catch (RuntimeException e) {
            throw new PostException(EFormPostCode.deleteFailure, e);
        }
        return PostBean.genePostMap(EFormPostCode.deleteSuccess);
    }


    /**
     * 查询单条字典信息数据
     *
     * @param dictType 字典类型
     */
    @GetMapping("/type/{dictType}")
    public Map<String, Object> querySysDictType (@PathVariable("dictType") String dictType) {
        SysDictTypeExample example = new SysDictTypeExample();
        // 为ALL则查询所有
        if (!dictType.equals(EncodeString.ALL)) {
            example.createCriteria().andNameEqualTo(dictType);
        }
        final List<SysDictType> sysDictTypes = iSysDictType.selectByExample(example);
        return PostBean.geneSimpleDataPostMap(sysDictTypes);
    }

    /**
     * 查询单条字典对应的字典项数据
     *
     * @param dictType 字典类型
     */
    @GetMapping("/item/{dictType}")
    public Map<String, Object> querySysDictItemByDictType (@PathVariable("dictType") String dictType) {
        SysDictItemExample example = new SysDictItemExample();
        example.createCriteria().andTypeEqualTo(dictType);
        final List<SysDictItem> sysDictItems = iSysDictItem.selectByExample(example);
        return PostBean.geneSimpleDataPostMap(sysDictItems);
    }

    /**
     * 查询字典信息结构
     */
    @GetMapping("/type/structure")
    public Map<String, Object> querySysDictTypeStructure () {
        final List<DictItemDto> dictItems = iSysDictItem.queryDictItemStructure();
        return PostBean.geneSimpleDataPostMap(dictItems);
    }

}