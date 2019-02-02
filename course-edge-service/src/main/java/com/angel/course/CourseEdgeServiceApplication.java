package com.angel.course;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.angel.course.com.angel.course.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JingXiang Bi
 * @date 2019/1/29
 */
@SpringBootApplication
@EnableDubboConfiguration
public class CourseEdgeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseEdgeServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        CourseFilter courseFilter = new CourseFilter();
        filterRegistrationBean.setFilter(courseFilter);

        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }
}
