package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName:SetmealMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/1下午7:35
 * @Version:
 */

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类ID查询套餐数量
     *
     * @param categoryId 分类ID
     * @return 套餐数量
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId};")
    Integer countByCategoryId(Long categoryId);
}
