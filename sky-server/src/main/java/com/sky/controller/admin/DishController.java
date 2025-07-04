package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * ClassName:DishController
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午3:48
 * @Version:
 */

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品管理接口")
public class DishController {

    @Autowired
    DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);

        //构造redis中的key
        String key = "dish_" + dishDTO.getCategoryId();
        cleanCache(key); // 清除特定分类的菜品缓存
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询菜品")
    public Result<PageResult> page(DishPageQueryDTO dishpagequerydto){
        log.info("分页查询菜品：{}", dishpagequerydto);
        PageResult pageResult = dishService.pageQuery(dishpagequerydto);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation(value = "删除菜品")
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除菜品：{}", ids);
        dishService.deleteBatch(ids);

        cleanCache("dish_*"); // 清除所有菜品相关的缓存
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询菜品")
    public Result<DishVO> getById(@PathVariable Long id){
        log.info("根据ID查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation(value = "更新菜品")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("更新菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);

        cleanCache("dish_*"); // 清除所有菜品相关的缓存
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用或禁用菜品")
    public Result<String> startOrStop(@PathVariable Integer status,Long id){
        log.info("修改菜品状态：{}，菜品ID：{}", status, id);
        dishService.startOrStop(status, id);

        cleanCache("dish_*"); // 清除所有菜品相关的缓存
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "根据分类ID查询菜品列表")
    public Result<List<Dish>> list(Long categoryId){
        log.info("根据分类ID查询菜品列表：{}", categoryId);
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys); // 删除所有匹配的缓存，确保数据一致性
    }
}
