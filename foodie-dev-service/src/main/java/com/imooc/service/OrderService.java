package com.imooc.service;

import com.imooc.bo.SubmitOrderBO;
import com.imooc.pojo.Carousel;

import java.util.List;

public interface OrderService {

    /**
     * 创建订单
     * @param submitOrderBO
     * @return
     */
    String createOrder(SubmitOrderBO submitOrderBO);

}
