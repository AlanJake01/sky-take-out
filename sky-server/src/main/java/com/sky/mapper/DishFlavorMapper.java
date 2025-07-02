package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName:DishFlavorMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午4:11
 * @Version:
 */

@Mapper
public interface DishFlavorMapper {

    void insertBatch(List<DishFlavor> flavors);

    @Delete("delete from dish_flavor where dish_id = #{dishId};")
    void deleteByDishId(Long dishID);

    void deleteByDishIds(List<Long> dishIds);

    @Select("select * from dish_flavor where dish_id = #{dishId};")
    List<DishFlavor> getByDishId(Long dishId);
}
