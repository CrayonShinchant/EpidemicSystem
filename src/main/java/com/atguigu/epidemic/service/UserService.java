package com.atguigu.epidemic.service;


import com.atguigu.epidemic.beans.UserInfo;

/**
 * 业务处理
 */
public interface UserService {
    /**
     * 根据用户的账号获取用户信息
     */
    UserInfo findByAccount(String account);
}
