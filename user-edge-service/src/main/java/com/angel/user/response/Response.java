package com.angel.user.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JingXiang Bi
 * @date 2019/1/16
 */
@Data
public class Response implements Serializable {

    public static final Response USERNAME_PASSWORD_INVALID = new Response(1001, "username or password invalid");

    private int code;

    private String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
