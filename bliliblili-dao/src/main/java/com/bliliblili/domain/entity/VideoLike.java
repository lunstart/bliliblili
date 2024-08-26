package com.bliliblili.domain.entity;


import lombok.Data;

import java.util.Date;

@Data
public class VideoLike {

    // 主键
    private Long id;

    // 用户id
    private Long userId;

    // 视频id
    private Long videoId;

    // 创建时间
    private Date createTime;

}
