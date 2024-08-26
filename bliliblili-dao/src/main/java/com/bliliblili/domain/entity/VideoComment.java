package com.bliliblili.domain.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VideoComment {

    //主键id
    private Long id;

    //视频id
    private Long videoId;

    //用户id
    private Long userId;

    //评论内容
    private String comment;

    //回复用户id
    private Long replyUserId;

    //根评论id
    private Long rootId;

    //评论类型
    private Date createTime;

    //更新时间
    private Date updateTime;

    //子评论列表
    private List<VideoComment> childList;

    //用户信息
    private UserInfo userInfo;

    //回复用户信息
    private UserInfo replyUserInfo;

}
