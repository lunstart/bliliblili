package com.bliliblili.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserFollowing {
    private Long id;

    //用户id
    private Long userId;

    //关注id
    private Long followingId;

    //分组id
    private Long groupId;

    //创建时间
    private LocalDateTime createTime;

}
