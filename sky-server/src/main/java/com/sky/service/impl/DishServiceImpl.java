package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
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

    @Autowired
    SetMealDishMapper setMealDishMapper;

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

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishpagequerydto) {
        PageHelper.startPage(dishpagequerydto.getPage(),dishpagequerydto.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishpagequerydto);
        Long total = page.getTotal();
        List<DishVO> records = page.getResult();
        PageResult pageresult = new PageResult(total, records);
        return pageresult;
    }

    @Transactional
    @Override
    public void deleteBatch(List<Long> ids) {
        //检查菜品是否在售
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        //检查菜品是否关联套餐
        List<Long> setmealIds = setMealDishMapper.getSetmealByDishIds(ids);
        if(setmealIds != null && setmealIds.size() > 0){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        /*//删除菜品
        for (Long id : ids) {
            dishMapper.deleteById(id);
            //删除菜品口味
            dishFlavorMapper.deleteByDishId(id);
        }*/
        //优化为批量删除
        dishMapper.deleteByIds(ids);
        dishFlavorMapper.deleteByDishIds(ids);


    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        Dish dish = dishMapper.getById(id);

        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);

        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish,dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        //修改菜品基本信息
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);

        dishMapper.update(dish);

        //删除原有口味数据
        dishFlavorMapper.deleteByDishId(dish.getId());

        //插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors != null && flavors.size() > 0){
            for (DishFlavor flavor : flavors) {
                flavor.setDishId(dishDTO.getId()); // 设置菜品ID
            }
        }
        dishFlavorMapper.insertBatch(flavors);
    }
}
