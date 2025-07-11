package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:OrderDetailMapper
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/9下午12:25
 * @Version:
 */

@Mapper
public interface OrderDetailMapper {
    void insertBatch(List<OrderDetail> orderDetailList);
}
