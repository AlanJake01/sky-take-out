package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    void update(Orders orders);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);

    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);
}
