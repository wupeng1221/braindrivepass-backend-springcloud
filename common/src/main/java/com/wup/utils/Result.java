package com.wup.utils;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : R  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2023/12/13  22:40
 */
@Data
@SuppressWarnings("all")
public class Result<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.msg = "success";
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}