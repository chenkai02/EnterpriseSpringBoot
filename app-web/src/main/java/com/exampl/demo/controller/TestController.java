package com.exampl.demo.controller;
import com.exampl.demo.entity.TesUser;
import com.exampl.demo.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Log4j2
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String demo() {
        return testService.findAll();
    }

    @GetMapping("/getUsers")//获取User信息接口
    public List<TesUser> getUsers() {
        List<TesUser> users = testService.getAll();
        return users;
    }

    //测试日志输出功能
    @GetMapping("query")
    public List<TesUser> query(){
        log.info("===========>开始查询数据库");
        List<TesUser> list = testService.getAll();
        log.info("======>>>: 数据库查询完成");
        System.out.println(list);
        return list;
    }


}
