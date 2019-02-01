package com.angel.course.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.angel.course.dto.CourseDTO;
import com.angel.course.mapper.CourseMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JingXiang Bi
 * @date 2019/1/29
 */
@Service
public class CourseServiceImpl implements ICourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<CourseDTO> courseList() {
        return courseMapper.listCourse();
    }
}
