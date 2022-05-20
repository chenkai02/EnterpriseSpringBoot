package com.exampl.demo.service.impl;

import com.exampl.demo.entity.TesUser;
import com.exampl.app.mapper.TestUserMapper;
import com.exampl.demo.service.TestService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Component
public class TestServiceImpl implements TestService {

    @Resource
    private TestUserMapper testUserMapper;

    @Override
    public List<TesUser> getAll() {
        return testUserMapper.findAll();
    }

    @Override
    public String findAll() {
        return "调用app-service子方法中的findAll()方法";
    }
}
