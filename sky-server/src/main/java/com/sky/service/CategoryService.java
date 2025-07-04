package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * ClassName:CategoryService
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/1下午5:52
 * @Version:
 */


public interface CategoryService {

    void save(CategoryDTO categoryDTO);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    List<Category> list(Integer type);

    void startOrStop(Integer status, Long id);

    void update(CategoryDTO categoryDTO);

    void deleteById(Long id);
}
