package com.wup.service;

import com.wup.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wup.utils.Result;

/**
* @author brainwu
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-01-30 03:12:48
*/
public interface UserService extends IService<User> {

    Long getIdByUsername(String username);

    Result<?> sendVerificationCode(String email);

    Result<?> createAccount(String username, String password, String phone);

    Result<?> login(String username, String password);

    Result<?> logout(String username);
}
