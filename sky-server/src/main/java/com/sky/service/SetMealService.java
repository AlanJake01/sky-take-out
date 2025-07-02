package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;

/**
 * ClassName:SetMealService
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午6:46
 * @Version:
 */
public interface SetMealService {
    void save(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
