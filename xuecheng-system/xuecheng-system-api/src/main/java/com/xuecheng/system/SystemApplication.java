package com.xuecheng.system;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author tangcw
 * @Desc
 * @date 2023/3/29 14:42
 */
@EnableScheduling
@EnableSwagger2Doc
@SpringBootApplication
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
