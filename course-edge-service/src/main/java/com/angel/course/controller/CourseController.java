package com.angel.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.angel.course.dto.CourseDTO;
import com.angel.course.service.ICourseService;
import com.angel.thrift.user.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Reference(interfaceClass = ICourseService.class)
    private ICourseService iCourseService;

    @GetMapping("/courseList")
    public List<CourseDTO> courseList(HttpServletRequest request){
        UserDTO user = (UserDTO)request.getAttribute("user");
        System.out.println(user.toString());

        return iCourseService.courseList();
    }
}
