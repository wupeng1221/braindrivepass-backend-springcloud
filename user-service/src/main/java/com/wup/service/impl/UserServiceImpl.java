package com.wup.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wup.constants.RedisConstants;
import com.wup.entity.User;
import com.wup.mapper.UserMapper;
import com.wup.service.UserService;
import com.wup.utils.MailUtil;
import com.wup.utils.Result;
import com.wup.utils.SHA256;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author brainwu
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-01-30 03:12:48
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<?> sendVerificationCode(String email) {
        //生成六位随机数字
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        //存入 redis
        String key = RedisConstants.USER_VERIFY_CODE_PREFIX + email;
        stringRedisTemplate.opsForValue().set(key, code, RedisConstants.USER_VERIFY_CODE_EXPIRE, RedisConstants.USER_VERIFY_CODE_EXPIRE_TIME);
        //发送邮件
        MailUtil.sendVerificationCode(email, code);
        return Result.success(null);
    }

    public Result<?> updatePassword(String username, String password, String verificationCode) {
        // redis查询是否存在验证码
        String verifyInRedis = stringRedisTemplate.opsForValue().get(RedisConstants.USER_VERIFY_CODE_PREFIX + username);
        if (verifyInRedis == null || verifyInRedis.isEmpty() || !verifyInRedis.equals(verificationCode)) {
            return Result.error("验证码错误");
        }
        boolean update = userService.update(new LambdaUpdateWrapper<User>().eq(User::getUsername, username).set(User::getPassword, SHA256.encode(password)));
        if (update) {
            return Result.success("修改成功");
        }
        return Result.error("系统错误，请重试");
    }

    @Override
    public Result<?> createAccount(String username, String password, String phone) {
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (one != null) {
            return Result.error("该邮箱已经被注册");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(SHA256.encode(password));
        user.setPhone(phone);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        boolean saved = userService.save(user);
        return saved ? Result.success(null) : Result.error("注册失败");
    }

    @Override
    public Result<?> login(String username, String password) {
        // 密码加密
        String encodedPasswd = SHA256.encode(password);
        // 查询数据库
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user == null) {//未注册
            return Result.error("该邮箱还未注册");
        }
        if (!user.getPassword().equals(encodedPasswd)) {//密码错误
            return Result.error("密码错误");
        }
        // 登录认证
        StpUtil.login(username);
        return Result.success(null);
    }

    @Override
    public Long getIdByUsername(String username) {
        return userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username)).getId();
    }

    @Override
    public Result<?> logout(String username) {
        StpUtil.logout(username);
        return Result.success(null);
    }
}




