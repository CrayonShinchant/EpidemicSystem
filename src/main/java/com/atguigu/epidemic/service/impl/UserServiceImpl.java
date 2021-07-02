package com.atguigu.epidemic.service.impl;


import com.atguigu.epidemic.beans.UserInfo;
import com.atguigu.epidemic.mapper.UserMapper;
import com.atguigu.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserInfo findByAccount(String account) {
        return userMapper.findByAccount(account);
    }
}
