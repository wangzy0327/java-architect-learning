package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {

    Stu getStuInfo(Integer id);

    void saveStu();

    void updateStu(int id);

    void deleteStu(int id);

    void saveParent();

    void saveChildren();

    void saveChild1();

    void saveChild2();
}
