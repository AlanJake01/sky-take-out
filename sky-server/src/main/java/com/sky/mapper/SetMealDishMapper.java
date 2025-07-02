package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:SetMealDishMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午4:57
 * @Version:
 */

@Mapper
public interface SetMealDishMapper {
    /**
     * 根据菜品ID列表查询套餐ID
     *
     * @param dishIds 菜品ID列表
     * @return 套餐ID列表
     */
    List<Long> getSetmealByDishIds(List<Long> dishIds);
}
