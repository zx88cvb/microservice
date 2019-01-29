package com.angel.course;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JingXiang Bi
 * @date 2019/1/29
 */
@SpringBootApplication
@EnableDubboConfiguration
public class CourseDubboServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseDubboServiceApplication.class, args);
    }
}
