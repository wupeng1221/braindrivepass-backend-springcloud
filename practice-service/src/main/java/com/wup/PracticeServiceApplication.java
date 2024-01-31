package com.wup;


import com.wup.client.UserClient;
import com.wup.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.wup.mapper")
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class, clients = UserClient.class)
public class PracticeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PracticeServiceApplication.class);
    }
}