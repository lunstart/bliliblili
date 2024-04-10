package com.bliliblili.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/6 16:34
 * @ 注释 用户动态实体类
 */
@Data
public class UserMoments {
    private Long id;

    private Long userId;

    //动态类型：0视频，1直播，2动态专栏
    private String type;

    //内容详情id
    private Long contentId;

    //创建时间
    private LocalDateTime createTime;

    //跟新时间
    private LocalDateTime updateTime;
}
