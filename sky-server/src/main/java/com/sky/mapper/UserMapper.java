package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * ClassName:UserMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/3下午10:40
 * @Version:
 */

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);


    @AutoFill(OperationType.INSERT)
    void insert(User user);

    @Select("select * from user where id = #{id}")
    User getById(Long id);

    Integer countByMap(Map map);
}
