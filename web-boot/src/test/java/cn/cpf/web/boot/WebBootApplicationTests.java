package cn.cpf.web.boot;

import cn.cpf.web.base.model.entity.SysDictItem;
import cn.cpf.web.base.model.example.SysDictItemExample;
import cn.cpf.web.dal.base.SysDictItemMapper;
import cn.cpf.web.service.base.api.ISysDictItem;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = WebBootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
class WebBootApplicationTests {

    @Autowired
    private ISysDictItem iSysDictItem;

    @Test
    void contextLoads() {
        final List<SysDictItem> sysDictItemList = iSysDictItem.selectByExample(new SysDictItemExample());
        System.out.println(sysDictItemList);

    }

}
