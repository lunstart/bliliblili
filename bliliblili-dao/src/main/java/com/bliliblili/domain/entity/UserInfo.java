package com.bliliblili.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {
    //主键id
    private Long id;

    //用户id
    private Long userId;

    //用户昵称
    private String nick;

    //签名
    private String sign;

    //头像
    private String avatar;

    //性别 0男,1女,2未知
    private String gender;

    //生日
    private String birth;

    //创建时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

    //新增属性
    //是否互关 true(是) false(否)
    private Boolean followed;
}
