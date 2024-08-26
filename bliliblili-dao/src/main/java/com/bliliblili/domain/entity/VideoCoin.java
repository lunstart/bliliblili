package com.bliliblili.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VideoCoin {

    // 主键
    private Long id;

    // 视频id
    private Long videoId;

    // 用户id
    private Long userId;

    // 投币数量
    private Integer amount;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
