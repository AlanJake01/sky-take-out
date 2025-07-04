package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * ClassName:DishService
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午3:51
 * @Version:
 */
public interface DishService {

    /**
     * 新增菜品
     *
     * @param dishDTO 菜品数据传输对象
     * @return 成功或失败的结果
     */
    void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishpagequerydto);

    void deleteBatch(List<Long> ids);

    DishVO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    void startOrStop(Integer status, Long id);

    List<Dish> list(Long categoryId);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    // 其他菜品相关方法可以在这里定义，例如更新、删除、查询等
}
