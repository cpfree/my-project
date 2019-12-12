package cn.cpf.web.boot.controller;

import cn.cpf.web.base.constant.dic.DicCommon;
import cn.cpf.web.base.lang.base.PostBean;
import cn.cpf.web.base.model.dto.DictItemDto;
import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.example.SysDictItemExample;
import cn.cpf.web.service.base.api.ISysDictItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * @date 2019/12/5 16:00
 **/
@Controller
@RequestMapping("/info")
@RestController
public class InformationController {

    @Autowired
    private ISysDictItem iSysDictItem;

    /**
     * @param type 字典类型
     */
    @GetMapping("/dict-item/{type}")
    public Map<String, Object> querySysDictItemByDictType (@PathVariable("type") String type) {
        SysDictItemExample example = new SysDictItemExample();
        example.createCriteria().andTypeEqualTo(type).andStateEqualTo(DicCommon.State.enable.getCode());
        final List<SysDictItem> sysDictItems = iSysDictItem.selectByExample(example);
        final List<DictItemDto> collect = sysDictItems.stream().map(DictItemDto::cnOf).collect(Collectors.toList());
        return PostBean.geneSimpleDataPostMap(collect);
    }

    /**
     * @param dictTypeList 字典类型
     */
    @GetMapping("/dict-item")
    public Map<String, Object> querySysDictItemArray (@RequestParam("type") List<String> dictTypeList) {
        SysDictItemExample example = new SysDictItemExample();
        example.createCriteria().andTypeIn(dictTypeList).andStateEqualTo(DicCommon.State.enable.getCode());
        final List<SysDictItem> sysDictItems = iSysDictItem.selectByExample(example);
        final Map<String, List<DictItemDto>> collect = sysDictItems.stream().collect(Collectors.groupingBy(SysDictItem::getType,
                Collectors.mapping(DictItemDto::cnOf, Collectors.toList())));
        return PostBean.geneSimpleDataPostMap(collect);
    }



}
