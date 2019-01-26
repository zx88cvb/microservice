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

    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response(1002, "mobile or email required");

    public static final Response SEND_VERIFYCODE_FAILED = new Response(1003, "send verify code failed");

    public static final Response VERIFY_CODE_INVALID = new Response(1004, "verifyCode invalid");

    public static final Response SUCCESS = new Response();

    private int code;

    private String message;

    public Response() {
        this.code = 0;
        this.message = "SUCCESS";
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Response exception(Exception e) {
        return new Response(9999, e.getMessage());
    }
}
