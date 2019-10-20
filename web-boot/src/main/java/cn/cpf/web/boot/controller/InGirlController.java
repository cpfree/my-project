package cn.cpf.web.boot.controller;

import cn.cpf.web.base.constant.postcode.ECommonPostCode;
import cn.cpf.web.base.constant.postcode.EFormPostCode;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.model.entity.InGirl;
import cn.cpf.web.base.model.entity.InGirlExample;
import cn.cpf.web.base.util.exception.PostException;
import cn.cpf.web.base.util.sql.PagingUtils;
import cn.cpf.web.boot.service.base.api.IInGirl;
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
 * @date 2019/10/20 02:25
 **/
@Controller
public class InGirlController {

    @Autowired
    IInGirl iInGirl;

    /**
     * 查询 inGirl 表分页数据
     *
     * @param minSendTime               最小发送时间
     * @param maxSendTime               最大发送时间
     * @param pageNumber                页号
     * @param pageSize                  页面大小
     */
    @RequestMapping("/inGirl/queryPageInfo")
    @ResponseBody
    public Map<String, Object> queryInGirlPageInfo (HttpServletRequest request,
                                                       @RequestParam(value = "minSendTime", required = false) Long minSendTime,
                                                       @RequestParam(value = "maxSendTime", required = false) Long maxSendTime,
                                                       @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        InGirlExample example = new InGirlExample();
        InGirlExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("update_time desc");
        // TODO 填写查找条件
        if (minSendTime != null) {
            criteria.andUpdateTimeGreaterThanOrEqualTo(new Date(minSendTime));
        }
        if (maxSendTime != null) {
            criteria.andUpdateTimeLessThan(new Date(maxSendTime));
        }
        PageInfo<InGirl> pageInfo = PagingUtils.selectStartPagingInfo(() -> iInGirl.selectByExample(example), pageNumber, pageSize);
        // 检查文件参数
        PostBean postBean = new PostBean();
        postBean.put("pageInfo", pageInfo);
        return postBean.toResultMap();
    }

    /**
     * 查询一条 inGirl 表数据
     */
    @PostMapping("/inGirl/queryOne/{guid}")
    @ResponseBody
    public Map<String, Object> queryInGirlOne (HttpServletRequest request, @PathVariable("guid") String guid) {
        final InGirl record = iInGirl.findByPrimaryKey(guid);
        if (record == null) {
            return PostBean.genePostMap(ECommonPostCode.dataLost);
        }
        PostBean postBean = new PostBean();
        postBean.put("record", record);
        return postBean.toResultMap();
    }

    /**
     * 更新 inGirl 表数据
     */
    @PostMapping("/inGirl/edit")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> editInGirl (HttpServletRequest request, @ModelAttribute InGirl record) {
        String id = record.getId();
        if (StringUtils.isBlank(id)) {
            return PostBean.genePostMap(ECommonPostCode.ILLEGAL_ARGUMENT_EXCEPTION);
        }
        record.setUpdateTime(new Date());
        final int row = iInGirl.updateByPrimaryKey(record);
        if (row > 0) {
            return PostBean.genePostMap();
        }
        return PostBean.genePostMap(ECommonPostCode.EXEC_FAILURE);

    }

    /**
     * 新增一条 inGirl 表数据
     */
    @PostMapping("/inGirl/add")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addInGirl (@ModelAttribute InGirl record) {
        final InGirl row = iInGirl.insert(record);
        if (row == null) {
            return PostBean.genePostMap(EFormPostCode.insertFailure);
        }
        return PostBean.genePostMap(EFormPostCode.insertSuccess);
    }

    /**
     * 删除 inGirl 表数据
     */
    @PostMapping("/inGirl/remove")
    @ResponseBody
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> removeInGirl (@RequestParam("idArr[]") String[] idArr) {
        try {
            Arrays.stream(idArr).forEach(iInGirl::deleteByPrimaryKey);
        } catch (RuntimeException e) {
            throw new PostException(EFormPostCode.deleteFailure, e);
        }
        return PostBean.genePostMap(EFormPostCode.deleteSuccess);
    }

}