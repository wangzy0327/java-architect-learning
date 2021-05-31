package com.imooc.service;

import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;

public interface UserService {

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBO
     * @return
     */
    Users createUser(UserBO userBO);

}
