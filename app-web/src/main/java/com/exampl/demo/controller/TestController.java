package com.exampl.demo.controller;

import com.exampl.demo.entity.TesUser;
import com.exampl.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/")
    public String demo() {
        return testService.findAll();
    }

    @GetMapping("/getUsers")//获取User信息接口
    public List<TesUser> query() {
        List<TesUser> users = testService.getAll();
        return users;
    }


}
