package com.wup.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName : DefaultFeignConfiguration  //类名
 * @Description :   //描述
 * @Author : brainwu //作者
 * @Date: 2024/1/30  01:02
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.FULL;
    }
}
