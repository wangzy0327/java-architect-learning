package com.imooc.controller;

import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

//忽略不在 swagger2中显示
@ApiIgnore
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    //请求路径 localhost:8088/getStu/1001 这样需要映射而这里并没有所以返回404
    //请求路径 localhost:8088/getStu?id=1001
    @GetMapping("/getStu")
    public Object getStu(Integer id){
        return stuService.getStuInfo(id);
    }

    @PostMapping("/saveStu")
    public Object saveStu(){
        stuService.saveStu();
        return "OK";
    }

    @PostMapping("/updateStu")
    public Object updateStu(Integer id){
        stuService.updateStu(id);
        return "OK";
    }

    @PostMapping("/deleteStu")
    public Object deleteStu(Integer id){
        stuService.deleteStu(id);
        return "OK";
    }


}
