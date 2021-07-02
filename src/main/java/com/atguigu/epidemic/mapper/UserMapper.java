package com.atguigu.epidemic.mapper;


import com.atguigu.epidemic.beans.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * 数据访问层
 *      实现对数据库的CURD
 */
@Mapper

public interface UserMapper {
    /**
     * 根据账户查询对应的用户信息
     */
    @Select(value = "select user_id,account,password,user_name from user where account = #{account} and  del_flag=0")
    UserInfo findByAccount(String account);
}
