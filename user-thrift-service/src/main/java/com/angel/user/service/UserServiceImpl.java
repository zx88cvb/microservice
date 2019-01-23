package com.angel.user.service;

import com.angel.thrift.user.UserInfo;
import com.angel.thrift.user.UserService;
import com.angel.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户接口实现
 * @author JingXiang Bi
 * @date 2019/1/15
 */
@Service

//@Transactional 会导致thrift read time out 原因不明
public class UserServiceImpl implements UserService.Iface {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getUserByName(String username) throws TException {
        return userMapper.getUserByName(username);
    }

    @Override
    public void regiserUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
