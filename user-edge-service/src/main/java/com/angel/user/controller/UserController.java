package com.angel.user.controller;

import com.angel.thrift.user.UserInfo;
import com.angel.user.conf.redis.RedisClient;
import com.angel.user.dto.UserDTO;
import com.angel.user.response.LoginResponse;
import com.angel.user.response.Response;
import com.angel.user.thrift.ServiceProvider;
import com.angel.user.utils.MD5Util;
import com.angel.user.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private RedisClient redisClient;

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

        UserDTO userDTO = new UserDTO().converFor(userInfo);

        // 缓存用户
        redisClient.set(token, userDTO, 3600);

        return new LoginResponse(token);
    }

    /**
     * 发送验证码
     * @param mobile 手机
     * @param email 邮箱
     * @return Response
     */
    @PostMapping("/sendVerifyCode")
    public Response sendVerifyCode(@RequestParam(required = false) String mobile,
                                   @RequestParam(required = false) String email) {
        String message = "Verify code is: ";
        String code = TokenUtil.randomCode("0123456789", 6);

        try {
            boolean result = false;
            if (StringUtils.isNotBlank(mobile)) {
                result= serviceProvider.getMessageService().sendMobileMessage(mobile, message + code);
                redisClient.set(mobile, code);
            } else if (StringUtils.isNotBlank(email)){
                result =serviceProvider.getMessageService().sendEmailMessage(email, message + code);
                redisClient.set(email, code);
            } else {
                return Response.MOBILE_OR_EMAIL_REQUIRED;
            }

            if (result) {
                return Response.SEND_VERIFYCODE_FAILED;
            }
        }
        catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }

        return Response.SUCCESS;
    }

    @PostMapping("/register")
    public Response register(String username,
                             String password,
                             @RequestParam(required = false) String mobile,
                             @RequestParam(required = false) String email,
                             String verifyCode){
        if (StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if (StringUtils.isNotBlank(mobile)) {
            String redisCode = redisClient.get(mobile);
            if (!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        } else {
            String redisCode = redisClient.get(email);
            if (!verifyCode.equals(redisCode)) {
                return Response.VERIFY_CODE_INVALID;
            }
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(MD5Util.getMd5(password));
        userInfo.setEmail(email);
        try {
            serviceProvider.getUserService().regiserUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }
        return Response.SUCCESS;
    }
}
