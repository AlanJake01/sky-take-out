package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ShopController
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/3下午9:02
 * @Version:
 */

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@Api(tags = "用户端店铺相关接口")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation(value = "查询店铺状态")
    public Result<Integer> getStatus(){
        log.info("查询店铺状态");
        Integer status = (Integer)redisTemplate.opsForValue().get(KEY);
        return Result.success(status);
    }
}
