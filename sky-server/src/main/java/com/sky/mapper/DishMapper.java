package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName:DishMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/1下午7:33
 * @Version:
 */

@Mapper
public interface DishMapper {

    /**
     * 根据分类ID查询菜品数量
     *
     * @param categoryId 分类ID
     * @return 菜品数量
     */
    @Select("select count(id) from dish where category_id = #{categoryId};")
    Integer countByCategoryId(Long categoryId);


    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);
}
