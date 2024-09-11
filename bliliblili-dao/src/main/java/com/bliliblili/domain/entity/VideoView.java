package com.bliliblili.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoView {

    //主键id
    private Long id;

    //视频id
    private Long videoId;

    //用户id
    private Long userId;

    //客户端id
    private String clientId;

    //客户端ip
    private String ip;

    //创建时间
    private Date createTime;

}
