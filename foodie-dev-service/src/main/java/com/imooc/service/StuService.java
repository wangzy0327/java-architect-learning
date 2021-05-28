package com.imooc.service;

import com.imooc.pojo.Stu;
import org.springframework.stereotype.Service;

public interface StuService {

    Stu getStuInfo(Integer id);

    void saveStu();

    void updateStu(int id);

    void deleteStu(int id);
}
