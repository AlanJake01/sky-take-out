package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;

import java.util.List;

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

    void deleteBatch(List<Long> ids);

    SetmealVO getByIdWithDish(Long id);

    void update(SetmealDTO setMealDTO);

    void startOrStop(Integer status, Long id);
}
