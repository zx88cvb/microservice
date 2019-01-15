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
@Transactional
public class UserServiceImpl implements UserService.Iface {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUserByName(String username) throws TException {
        return userMapper.getUserByName(username);
    }

    @Override
    public void regiserUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}
