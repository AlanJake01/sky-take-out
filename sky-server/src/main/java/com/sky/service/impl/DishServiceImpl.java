package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:DishServiceImpl
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午3:52
 * @Version:
 */

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DishMapper dishMapper;

    @Autowired
    DishFlavorMapper dishFlavorMapper;

    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        // 保存菜品基本信息
        dishMapper.insert(dish);
        // 获取菜品ID
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            //插入口味数据
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishId); // 设置菜品ID
            }
            dishFlavorMapper.insertBatch(flavors);
        }
    }
}
