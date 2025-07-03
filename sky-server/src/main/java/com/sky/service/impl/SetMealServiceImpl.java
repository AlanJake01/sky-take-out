package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.exception.SetmealEnableFailedException;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetMealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private DishMapper dishMapper;

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

    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());

        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        //todo 之后优化为一次sql
        for (Long id : ids) {
            //在售的套餐不能删除
            Setmeal setmeal = setmealMapper.getById(id);
            if(setmeal.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }

        for (Long id : ids) {
            setmealMapper.deleteById(id);
            setMealDishMapper.deleteBySetmealId(id);
        }
    }

    @Override
    public SetmealVO getByIdWithDish(Long id) {
        Setmeal setmeal = setmealMapper.getById(id);
        List<SetmealDish> setmealDishes = setMealDishMapper.getBySetmealId(id);

        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal,setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);
        return setmealVO;
    }

    @Override
    @Transactional
    public void update(SetmealDTO setMealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setMealDTO,setmeal);

        //更新套餐基本信息
        setmealMapper.update(setmeal);
        //删除原有的套餐菜品关联关系
        Long setmealId = setMealDTO.getId();

        setMealDishMapper.deleteBySetmealId(setmealId);

        //新增套餐和菜品的关联关系
        List<SetmealDish> setmealDishes = setMealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealId);
        }

        setMealDishMapper.insertBatch(setmealDishes);

    }

    @Override
    public void startOrStop(Integer status, Long id) {
        //如果是起售套餐，先判断套餐内是否包含停售菜品，如有，则不能起售
        if(status == StatusConstant.ENABLE){
            List<Dish> dishes = dishMapper.getBySetmealId(id);
            if(dishes != null && dishes.size() > 0){
                for (Dish dish : dishes) {
                    if(dish.getStatus() == StatusConstant.DISABLE){
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                }
            }
        }

        //更新套餐状态
        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .build();
        setmealMapper.update(setmeal);
    }
}
