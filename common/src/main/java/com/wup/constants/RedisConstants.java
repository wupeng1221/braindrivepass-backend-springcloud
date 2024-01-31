package com.wup.constants;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName : RedisConstants  //类名
 * @Description : redis的常量类  //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/28  21:39
 */
public class RedisConstants {
    public static final String USER_VERIFY_CODE_PREFIX = "user:VrCode:";
    public static final Long USER_VERIFY_CODE_EXPIRE = 2L;
    public static final TimeUnit USER_VERIFY_CODE_EXPIRE_TIME = TimeUnit.MINUTES;
}
