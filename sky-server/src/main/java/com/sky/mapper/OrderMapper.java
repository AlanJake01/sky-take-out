package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:OrderMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/9下午12:25
 * @Version:
 */

@Mapper
public interface OrderMapper {
    void insert(Orders orders);
}
