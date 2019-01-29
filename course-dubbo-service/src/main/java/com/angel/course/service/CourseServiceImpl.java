package com.angel.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.angel.course.dto.CourseDTO;

import java.util.List;

/**
 * @author JingXiang Bi
 * @date 2019/1/29
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Override
    public List<CourseDTO> courseList() {
        return null;
    }
}
