package com.exampl.demo.controller;

import com.exampl.demo.domain.common.util.ResultUtil;
import com.exampl.demo.domain.entity.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author 1
 */
@RestController
@RequestMapping("/SecKill")
@Log4j2
public class SecKillController {


   /**
    *秒杀过程
    * */
   @GetMapping("/doSec")
   @ApiOperation("秒杀测试")
    public Result doSecKill(String uid,String prodid){

        //1  uid和prodid非空判断
        if (uid == null || prodid ==null){
            return ResultUtil.error(-1,"用户id && 商品id不能为空");
        }

        //2 链接redis
        Jedis jedis = new Jedis("192.168.31.175",6379);
        jedis.auth("123456");
        //3 拼接kill
        //库存key
        String keKey = "sk:"+prodid+":qt";
        //秒杀成功的用户key
        String userKey = "sk:"+prodid+":user";

        //监视库存
         jedis.watch(keKey);
        //4 获取库存 如果库存为null 则没开始秒杀
        String kc = jedis.get(keKey);
        if (kc == null){
            jedis.close();
            return ResultUtil.error(-1,"秒杀还未开始");
        }

        //5 判断用户是否重复秒杀
        if (jedis.sismember(userKey ,uid)){
            jedis.close();
            return ResultUtil.error(-1,"你已经秒杀过了");
        }
        //6 判断商品的数量小于1了 秒杀结束
        if (Integer.parseInt(kc)<0){
            return ResultUtil.error(-1,"秒杀已经结束了");
        }
        //7 秒杀过程
        //使用事务
        Transaction multi = jedis.multi();
        //组队操作
        multi.decr(keKey);
        multi.sadd(userKey,uid);

        //执行
        List<Object> results = multi.exec();
        if (results == null || results.size() ==0){
            jedis.close();
            return ResultUtil.error(-1,"秒杀失败了");
        }
        //库存-1
        jedis.decr(keKey);
        //秒杀成功的用户放到清单里面
        jedis.sadd(userKey,uid);

        return  ResultUtil.success("秒杀成功");


    }
}
