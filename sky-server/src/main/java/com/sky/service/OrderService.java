package com.sky.service;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;

/**
 * ClassName:OrderService
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/9下午12:21
 * @Version:
 */
public interface OrderService {
  OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

  OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

  void paySuccess(String outTradeNo);
}
