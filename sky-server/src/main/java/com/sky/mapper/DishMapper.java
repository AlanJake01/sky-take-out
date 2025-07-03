package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    Page<DishVO> pageQuery(DishPageQueryDTO dishpagequerydto);

    @Select("select * from dish where id = #{id};")
    Dish getById(Long id);

    @Delete("delete from dish where id = #{id};")
    void deleteById(Long id);

    void deleteByIds(List<Long> ids);

    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    List<Dish> list(Dish dish);

    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id =#{id}")
    List<Dish> getBySetmealId(Long id);
}
