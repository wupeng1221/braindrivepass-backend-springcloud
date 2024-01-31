package com.wup;

import com.wup.client.PracticeClient;
import com.wup.client.UserClient;
import com.wup.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.wup.mapper")
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class, clients = {PracticeClient.class, UserClient.class})
public class ExamServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamServiceApplication.class);
    }
}