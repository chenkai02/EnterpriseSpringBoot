package com.exampl.demo.controller;
import com.exampl.demo.domain.common.util.RedisUtil;
import com.exampl.demo.domain.common.util.ResultUtil;
import com.exampl.demo.domain.entity.Result;
import com.exampl.demo.domain.enums.ResultEnum;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;


/**
 * @author 1
 */
@RestController
@RequestMapping("/redis")
@Log4j2
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    private final RedisTemplate redisTemplate;

    public RedisController(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @GetMapping ()
    public Result getRedis(String key){
        try {
             return ResultUtil.success(redisUtil.get(key));
        }catch (Exception e){
            log.error("查询失败", e);
            return ResultUtil.error(-1,"查询失败");
        }
    }

    @PostMapping ("/save")
    public Result save(String key , String Value){
        try {
            redisTemplate.opsForValue().set(key ,Value);
            return ResultUtil.success();
        }catch (Exception e){
            log.error("内部错误", e);
            return ResultUtil.error(-1,"内部错误");
        }
    }
}
