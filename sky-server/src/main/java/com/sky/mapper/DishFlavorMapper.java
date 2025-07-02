package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

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
}
