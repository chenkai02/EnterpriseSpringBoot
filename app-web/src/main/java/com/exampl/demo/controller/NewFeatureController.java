package com.exampl.demo.controller;

import com.exampl.demo.entity.TesUser;
import com.exampl.demo.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "stream测试类")
public class NewFeatureController {

    @Autowired
    private TestService testService;

    List<String> strArr = Arrays.asList("18","24","3","5","19");
    /**
     * java8新特性，stream方法的filter
     * filter 通过设置的条件过滤元素
     *
     * @param number
     * @return result
     */
    @GetMapping("/filter")
    @ApiOperation("filter测试")
    public List<String> newFeature(String number){
        List<String> result = strArr.stream().filter(str -> !number.equals(str)&&
                !str.equals("")).collect(Collectors.toList());//去除输入的变量返回list
        return  result;
    }
    /**
     * java8新特性，stream方法的map
     * map 对对象中的某个属性进行提取,或对对象中的元素进行处理
     *
     * @param
     * @return names
     */
    @GetMapping("/map")
    @ApiOperation("map测试")
    public List<String> newFeature(){
        List<TesUser> users = testService.getAll();
        List<String> names = users.stream().map(e -> e.getName()).collect(Collectors.toList());//只返回对象的名字属性
        return names;
    }
}
