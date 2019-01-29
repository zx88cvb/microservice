package com.angel.thrift.user.dto;

import lombok.Data;

/**
 * @author JingXiang Bi
 * @date 2019/1/29
 */
@Data
public class TeacherDTO extends UserDTO {
    private String intro;

    private int stars;
}
