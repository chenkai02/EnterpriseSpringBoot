package com.exampl.demo.service.impl;

import com.exampl.demo.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
  @Override
  public String findAll() {
    return "调用app-service子方法中的findAll()方法";
  }
}
