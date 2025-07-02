package com.sky.service;

import com.sky.dto.DishDTO;

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

    // 其他菜品相关方法可以在这里定义，例如更新、删除、查询等
}
