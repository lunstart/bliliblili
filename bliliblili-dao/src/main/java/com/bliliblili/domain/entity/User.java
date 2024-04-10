package com.bliliblili.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    //主键id
    private Long id;

    //手机号/账号
    private String phone;

    //邮箱
    private String email;

    //密码
    private String password;

    //md5盐值
    private String salt;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

    private UserInfo UserInfo;
}
