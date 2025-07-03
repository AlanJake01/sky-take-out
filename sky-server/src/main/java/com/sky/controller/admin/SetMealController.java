package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import com.wechat.pay.contrib.apache.httpclient.util.RsaCryptoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:SetMealController
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午6:46
 * @Version:
 */
@RestController
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐管理接口")
@Slf4j
public class SetMealController {
    @Autowired
    SetMealService setMealService;

    @PostMapping
    @ApiOperation(value = "新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐：{}", setmealDTO);
        setMealService.save(setmealDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询套餐")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询套餐：{}", setmealPageQueryDTO);
        PageResult pageResult = setMealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation(value = "删除套餐")
    public Result delete(@RequestParam List<Long> ids){
        log.info("删除套餐：{}", ids);
        setMealService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id){
        log.info("根据ID查询套餐：{}", id);
        SetmealVO setmealVO = setMealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    @PutMapping
    @ApiOperation(value = "修改套餐")
    public Result update(@RequestBody SetmealDTO setMealDTO){
        log.info("修改套餐：{}", setMealDTO);
        setMealService.update(setMealDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation(value = "启用或禁用套餐")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("启用或禁用套餐，状态：{}，ID：{}", status, id);
        setMealService.startOrStop(status, id);
        return Result.success();
    }
}
