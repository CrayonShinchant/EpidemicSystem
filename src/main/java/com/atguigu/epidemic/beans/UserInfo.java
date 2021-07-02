package com.atguigu.epidemic.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Integer userId;
    private String account;
    private String password;
    private String userName;
    private Integer delFlag;
}
