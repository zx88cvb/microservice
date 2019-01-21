package com.angel.user.response;

import lombok.Data;

/**
 * @author JingXiang Bi
 * @date 2019/1/21
 */
@Data
public class LoginResponse extends Response {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}
