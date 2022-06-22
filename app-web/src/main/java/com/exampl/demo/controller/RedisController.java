package com.exampl.demo.controller;
import com.exampl.demo.domain.common.util.RedisUtil;
import com.exampl.demo.domain.common.util.ResultUtil;
import com.exampl.demo.domain.entity.Result;
import com.exampl.demo.domain.enums.ResultEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 1
 */
@RestController
@RequestMapping("/redis")
@Log4j2
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    private  RedisTemplate redisTemplate;

    /***
     * 配置redis序列化
     * 如果用redis自带序列化数据会产生\xac\xed\x00\x05t\x00字符串
     */
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    public RedisController(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @GetMapping ()
    public Result getRedis(String key){
        try {
             return ResultUtil.success(redisUtil.get(key));
        }catch (Exception e){
            log.error("查询失败", e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),e.getMessage());
        }
    }

    @PostMapping ("/save")
    @ApiOperation("re   disSet测试")
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
