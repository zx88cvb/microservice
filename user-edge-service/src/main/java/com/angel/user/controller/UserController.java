package com.angel.user.controller;

import com.angel.thrift.user.UserInfo;
import com.angel.user.response.Response;
import com.angel.user.thrift.ServiceProvider;
import com.angel.user.utils.MD5Util;
import com.angel.user.utils.TokenUtil;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author JingXiang Bi
 * @date 2019/1/16
 */
@RestController
public class UserController {

    @Resource
    private ServiceProvider serviceProvider;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return Response对象
     */
    @PostMapping("/login")
    public Response login(String username, String password) {
        // 验证用户名密码
        UserInfo userInfo = null;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }

        if (userInfo == null) {
            return Response.USERNAME_PASSWORD_INVALID;
        }

        if (!userInfo.getPassword().equalsIgnoreCase(MD5Util.getMd5(password))) {
            return Response.USERNAME_PASSWORD_INVALID;
        }

        // 生成Token
        String token = TokenUtil.getToken();

        // 缓存用户

        return null;
    }
}
