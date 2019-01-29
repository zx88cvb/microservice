package com.angel.course.dto;

import com.angel.thrift.user.dto.TeacherDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author JingXiang Bi
 * @date 2019/1/29
 */
@Data
public class CourseDTO implements Serializable {
    private int id;

    private String title;

    private String description;

    private TeacherDTO teacher;
}
