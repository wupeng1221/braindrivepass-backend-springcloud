package com.wup.controller;

import com.wup.service.impl.UserServiceImpl;
import com.wup.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName : UserController  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/28  11:59
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    /**
     * 发送验证码
     * <p>
     * //* @param email 邮箱
     *
     * @return Result<?>
     */
    @GetMapping("/verificationCode")
    @ResponseBody
    public Result<?> sendVerificationCode(String email) {
        return userService.sendVerificationCode(email);
    }

    /**
     * 更新密码
     *
     * @param username         用户名
     * @param password         密码
     * @param verificationCode 验证码
     * @return Result<?>
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public Result<?> submitModifyPassword(@RequestParam(name = "email") String username,
                                          @RequestParam(name = "password") String password,
                                          @RequestParam(name = "verificationCode") String verificationCode) {
        return userService.updatePassword(username, password, verificationCode);
    }

    /**
     * 创建账户
     *
     * @param username 用户名
     * @param password 密码
     * @param phone    手机号
     * @return Result<?>
     */
    @PostMapping("/registry")
    @ResponseBody
    public Result<?> createAccount(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "phone") String phone) {
        return userService.createAccount(username, password, phone);
    }

    @PostMapping("/login")
    @ResponseBody
    public Result<?> login(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password) {
        return userService.login(username, password);
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestParam(name = "username") String username) {
        return userService.logout(username);
    }

    @GetMapping("/getIdByUsername")
    @ResponseBody
    public Long getIdByUsername(@RequestParam(name = "username") String username) {
        return userService.getIdByUsername(username);
    }
}



