package com.sky.service.impl;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetMealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:SetMealServiceImpl
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/2下午6:47
 * @Version:
 */
@Service
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    SetmealMapper setmealMapper;

    @Autowired
    SetMealDishMapper setMealDishMapper;

    @Override
    public void save(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        //新增套餐信息
        setmealMapper.save(setmeal);
        Long setmealId = setmeal.getId();

        //新增套餐和菜品的关联关系
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealId);
        }

        setMealDishMapper.insertBatch(setmealDishes);
    }
}
