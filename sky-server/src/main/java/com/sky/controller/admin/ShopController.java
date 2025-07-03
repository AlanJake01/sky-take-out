package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:ShopController
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/3下午8:56
 * @Version:
 */

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "商家端店铺相关接口")
public class ShopController {

    public static final String KEY = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation(value = "修改店铺状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("修改店铺状态：{}", status);
        // 将店铺状态存入Redis
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(KEY,status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation(value = "查询店铺状态")
    public Result<Integer> getStatus(){
        log.info("查询店铺状态");
        Integer status = (Integer)redisTemplate.opsForValue().get(KEY);

        return Result.success(status);
    }
}
