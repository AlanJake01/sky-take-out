package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName:CategoryMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/1下午6:03
 * @Version:
 */

@Mapper
public interface CategoryMapper {

    @Insert("insert into category(type,name, sort,status, create_time, update_time, create_user, update_user)"
            + " values (#{type}, #{name}, #{sort},#{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Category category);


    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);


    List<Category> list(Integer type);

    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);
}
