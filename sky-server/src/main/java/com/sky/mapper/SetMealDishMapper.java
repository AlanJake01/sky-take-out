package com.sky.mapper;

import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
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

    void update(Setmeal setMeal);

    void insertBatch(List<SetmealDish> setmealDishes);

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Long setmealId);
}
