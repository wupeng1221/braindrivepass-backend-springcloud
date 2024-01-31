package com.wup.client;

import com.wup.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "user-service",
        configuration = FeignInterceptor.class, // 请求拦截器 （关键代码）
        decode404 = true
)
public interface UserClient {
    @GetMapping("/user/getIdByUsername")
    Long getIdByUsername(@RequestParam("username") String username);
}

