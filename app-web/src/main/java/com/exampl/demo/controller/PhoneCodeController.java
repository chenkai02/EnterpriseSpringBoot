package com.exampl.demo.controller;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Random;
/**
 * @author 1
 */
@RestController
@RequestMapping("/PhoneCode")
@Log4j2
public class PhoneCodeController {
    /***
     * 验证码校验
     */
    @PostMapping("/getRedisCode")
    @ApiOperation("redis验证码校验")
    public  boolean getRedisCode(String phone ,String code){
        //链接redis
        Jedis jedis = new Jedis("192.168.31.175",6379);
        jedis.auth("123456");
        //验证码key
        String codeKey = "verifyCode"+phone+":code";
        String redisCode = jedis.get(codeKey);
        //判断
        if (redisCode.equals(code)){
            jedis.close();
            return true;
        }else {
            jedis.close();
            return false;
        }

    }


    /**
     * 让每个手机每天只能发送三次 在redis中设置过期时间
     */
    @PostMapping("/verifyCode")
    @ApiOperation("redis发送验证码")
    public   void verifyCode(String phone){
        //链接redis
        Jedis jedis = new Jedis("192.168.31.175",6379);
        jedis.auth("123456");
        //拼接key
        //手机发送次数key
        String countKey = "verifyCode"+phone+":count";
        //验证码key
        String codeKey = "verifyCode"+phone+":code";
        //每个手机只能发送三次
        String count = jedis.get(countKey);
        if (count == null){
            //没有发送次数 ，第一次发送
            //设置发送次数是1
            jedis.setex(countKey,24*60*60,"1");
        }else if (Integer.parseInt(count)<=2){
            //发送次数+1
            jedis.incr(codeKey);
        }else if (Integer.parseInt(count)>2){
            //发送超过三次
            System.out.println("发送次数已超过三次");
            jedis.close();
            return;
        }

        //发送的验证码放到redis里面去
        jedis.setex(codeKey , 120 ,getCode());
        jedis.close();

    }


    /***
     * 生成6位数验证码
     */
    public static  String getCode(){
        Random random = new Random();
        String code = "";
        for (int i  = 0;i< 6 ;i++){
           int rand = random.nextInt(10);
           code += rand;
        }
          return code;
    }
}
