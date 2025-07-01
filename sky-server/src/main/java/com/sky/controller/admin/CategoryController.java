package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:CategoryController
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/1下午5:45
 * @Version:
 */

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类管理接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param categoryDTO
     * @return
     */
    @ApiOperation(value = "新增分类")
    @PostMapping
    public Result<String> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类：{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    @ApiOperation(value = "分类分页查询")
    @GetMapping("/page")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询：{}", categoryPageQueryDTO);
        // 这里应该调用分类服务的分页查询方法
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @ApiOperation(value = "根据类型查询分类")
    @GetMapping("/list")
    public Result<List<CategoryDTO>> listByType(Integer type) {
        log.info("根据类型查询分类：{}", type);
        List<CategoryDTO> categoryList = categoryService.list(type);
        return Result.success(categoryList);
    }

    @ApiOperation(value = "启用禁用分类")
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("启用禁用分类：{}，id:{}", status, id);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    @ApiOperation(value = "修改分类")
    @PutMapping
    public Result<String> update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @ApiOperation(value = "根据id删除分类")
    @DeleteMapping
    public Result<String> deleteById(Long id){
        log.info("删除分类，id:{}", id);
        // 这里应该调用分类服务的删除方法
        categoryService.deleteById(id);
        return Result.success();
    }

}
