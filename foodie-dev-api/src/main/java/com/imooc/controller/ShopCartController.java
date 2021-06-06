package com.imooc.controller;

import com.imooc.bo.ShopCartBO;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//忽略不在 swagger2中显示
@Api(value = "购物车接口controller",tags = "购物车接口相关API")
@RestController
@RequestMapping("shopcart")
public class ShopCartController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @RequestBody ShopCartBO shopCartBO,
            HttpServletRequest request,
            HttpServletResponse response
            ){

        if(StringUtils.isBlank(userId))
            return IMOOCJSONResult.errorMsg("");

        System.out.println(shopCartBO);

        //TODO 分布式缓存
        //TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存

        return IMOOCJSONResult.ok();

    }


    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public IMOOCJSONResult del(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "itemSpecId", value = "商品规格id", required = true)
            @RequestBody String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ){

        if(StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId))
            return IMOOCJSONResult.errorMsg("参数不能为空");


        //TODO 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的数据

        return IMOOCJSONResult.ok();

    }

}
