package com.imooc.controller;

import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// swagger2 接口说明
@Api(value = "注册登录",tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @Transactional(propagation = Propagation.SUPPORTS)
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {
        //1.判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }
        //2.查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        //3.请求成功，用户名没有重复
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        // 0.判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
        // 1.查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        // 2.密码长度不能少于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能少于6位");
        }
        // 3.判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }
        // 4.实现注册
        Users userResult = userService.createUser(userBO);
        userResult = setNullProperty(userResult);

        CookieUtils.setCookie(request,response,"user",
                JsonUtils.objectToJson(userResult),true);

        //TODO 生成用户token,存入redis会话
        //TODO 同步购物车数据

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @Transactional(propagation = Propagation.SUPPORTS)
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        //0.判断用户名和密码必须不为空
        if (StringUtils.isBlank(username)
                || StringUtils.isBlank(password))
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        Users userResult = null;
        //1.实现登录
        try {
            userResult = userService.queryUserForLogin(username,
                    MD5Utils.getMD5Str(password));
            if (userResult == null)
                return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        } catch (Exception e) {
            e.printStackTrace();
        }

        userResult = setNullProperty(userResult);

        //给前端设置cookie,当前实现的代码后端是没有session内容的，无状态的
        //后端不保存session，节省内存
        //利用cookie通过JSessionID来标识会话
        CookieUtils.setCookie(request,response,"user",
                JsonUtils.objectToJson(userResult),true);

        //TODO 生成用户token,存入redis会话
        //TODO 同步购物车数据

        return IMOOCJSONResult.ok(userResult);
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setRealname(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @Transactional(propagation = Propagation.SUPPORTS)
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        //清除用户相关信息的cookie
        CookieUtils.deleteCookie(request,response,"user");

        // TODO 用户退出登录,需要清空购物车
        // TODO 分布式会话中需要清除用户数据

        return IMOOCJSONResult.ok();
    }

}
