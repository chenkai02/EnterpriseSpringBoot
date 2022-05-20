package com.exampl.demo.service;
import com.exampl.demo.entity.TesUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {

    List<TesUser> getAll();

    String findAll();
}
