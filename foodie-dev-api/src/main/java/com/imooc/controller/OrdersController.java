package com.imooc.controller;

import com.imooc.bo.SubmitOrderBO;
import com.imooc.pojo.UserAddress;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "订单相关",tags = {"订单相关的api接口"})
@RequestMapping("orders")
@RestController
public class OrdersController {


    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult list(
            @RequestBody SubmitOrderBO submitOrderBO
    ){
        System.out.println(submitOrderBO);
        // 1.创建订单
        // 2.创建订单以后，移除购物车中已结算（已提交）的商品
        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        return IMOOCJSONResult.ok();
    }


}
