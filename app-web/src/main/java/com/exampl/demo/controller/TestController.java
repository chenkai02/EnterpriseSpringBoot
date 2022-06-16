package com.exampl.demo.controller;
import com.exampl.demo.entity.TesUser;
import com.exampl.demo.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author 1
 */
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
        try {
            List<TesUser> users = testService.getAll();
            return users;
        }catch (Exception e){
            log.error("操作异常", e);
            return null;
        }

    }

    /**
     * 测试日志输出功能
     */
    @GetMapping("query")
    public List<TesUser> query(){
        log.info("===========>开始查询数据库");
        List<TesUser> list = testService.getAll();
        log.info("======>>>: 数据库查询完成");
        System.out.println(list);
        return list;
    }


    /**
     * 测试三元运算符
     */
    @GetMapping("ternary")
    public  int ternary(){
        int a = 10;
        int b = 0;
        int ternary = a>b ? a++ : b;
        return ternary+a;
    }


    public void transferAccount() {
        
    }

}
