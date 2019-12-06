package cn.cpf.web.boot.controller;

import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.EFormPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.model.entity.SysDictType;
import cn.cpf.web.base.model.example.SysDictTypeExample;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.base.util.sql.PagingUtils;
import cn.cpf.web.service.base.api.ISysDictType;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/05 05:09
 **/
@Controller
public class SysDictTypeController {

    @Autowired
    ISysDictType iSysDictType;

    /**
     * 查询 sysDictType 表分页数据
     *
     * @param minSendTime               最小发送时间
     * @param maxSendTime               最大发送时间
     * @param pageNumber                页号
     * @param pageSize                  页面大小
     */
    @RequestMapping("/sysDictType/queryPageInfo")
    @ResponseBody
    public Map<String, Object> querySysDictTypePageInfo (HttpServletRequest request,
                                                       @RequestParam(value = "minSendTime", required = false) Long minSendTime,
                                                       @RequestParam(value = "maxSendTime", required = false) Long maxSendTime,
                                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        SysDictTypeExample example = new SysDictTypeExample();
        SysDictTypeExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("update_time desc");
        // TODO 填写查找条件
        if (minSendTime != null) {
            criteria.andUpdateTimeGreaterThanOrEqualTo(new Date(minSendTime));
        }
        if (maxSendTime != null) {
            criteria.andUpdateTimeLessThan(new Date(maxSendTime));
        }
        PageInfo<SysDictType> pageInfo = PagingUtils.selectStartPagingInfo(() -> iSysDictType.selectByExample(example), pageNumber, pageSize);
        // 检查文件参数
        PostBean postBean = new PostBean();
        postBean.put("pageInfo", pageInfo);
        return postBean.toResultMap();
    }

    /**
     * 查询一条 sysDictType 表数据
     */
    @PostMapping("/sysDictType/queryOne/{guid}")
    @ResponseBody
    public Map<String, Object> querySysDictTypeOne (HttpServletRequest request, @PathVariable("guid") String guid) {
        final SysDictType record = iSysDictType.findByPrimaryKey(guid);
        if (record == null) {
            return PostBean.genePostMap(ECommonPostCode.dataLost);
        }
        PostBean postBean = new PostBean();
        postBean.put("record", record);
        return postBean.toResultMap();
    }

    /**
     * 更新 sysDictType 表数据
     */
    @PostMapping("/sysDictType/edit")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> editSysDictType (HttpServletRequest request, @ModelAttribute SysDictType record) {
        String name = record.getName();
        if (StringUtils.isBlank(name)) {
            return PostBean.genePostMap(ECommonPostCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }
        record.setUpdateTime(new Date());
        final int row = iSysDictType.updateByPrimaryKey(record);
        if (row > 0) {
            return PostBean.genePostMap();
        }
        return PostBean.genePostMap(ECommonPostCode.EXEC_FAILURE);

    }

    /**
     * 新增一条 sysDictType 表数据
     */
    @PostMapping("/sysDictType/add")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addSysDictType (@ModelAttribute SysDictType record) {
        final SysDictType row = iSysDictType.insert(record);
        if (row == null) {
            return PostBean.genePostMap(EFormPostCode.insertFailure);
        }
        return PostBean.genePostMap(EFormPostCode.insertSuccess);
    }

    /**
     * 删除 sysDictType 表数据
     */
    @PostMapping("/sysDictType/remove")
    @ResponseBody
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> removeSysDictType (@RequestParam("nameArr[]") String[] nameArr) {
        try {
            Arrays.stream(nameArr).forEach(iSysDictType::deleteByPrimaryKey);
        } catch (RuntimeException e) {
            throw new PostException(EFormPostCode.deleteFailure, e);
        }
        return PostBean.genePostMap(EFormPostCode.deleteSuccess);
    }

}