package com.bliliblili.service;

import com.bliliblili.domain.dto.VideoCollectionDTO;
import com.bliliblili.domain.entity.*;
import com.bliliblili.domain.jsonresponse.PageResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/14 19:45
 * @ 注释
 */
public interface VideoService {

    /**
     * 添加视频
     */
    void addVideos(Video video);

    /**
     * 分页查询视频列表
     */
    PageResult<Video> pageListVideos(Integer size, Integer no, String area);

    /**
     * 视频在线播放
     */
    void viewVideoOnlineBySlices(HttpServletRequest request, HttpServletResponse response, String url) throws Exception;

    /**
     * 视频点赞
     */
    void addVideoLike(Long videoId, Long userId);

    /**
     * 取消视频点赞
     */
    void deleteVideoLike(Long videoId, Long userId);

    /**
     * 获取视频点赞数
     */
    Map<String, Object> getVideoLikes(Long videoId, Long userId);

    /**
     * 添加视频收藏
     */
    void addVideoCollection(VideoCollectionDTO videoCollectionDTO, Long userId);

    /**
     * 取消视频收藏
     */
    void deleteVideoCollection(Long videoId, Long userId);

    /**
     * 获取视频收藏数
     */
    Map<String, Object> getVideoCollections(Long videoId, Long userId);

    /**
     * 添加视频投币
     */
    void addVideoCoins(VideoCoin videoCoin, Long userId);

    /**
     * 查询视频投币
     */
    Map<String, Object> getVideoCoins(Long videoId, Long userId);

    /**
     * 添加视频评论
     */
    void addVideoComment(VideoComment videoComment, Long userId);

    /**
     * 分页查询视频评论
     */
    PageResult<VideoComment> pageListVideoComments(Integer size, Integer no, Long videoId);

    /**
     * 获取视频详情
     */
    Map<String, Object> getVideoDetails(Long videoId);

    /**
     * 添加视频播放记录
     */
    void addVideoView(VideoView videoView, HttpServletRequest request);

    /**
     * 获取视频播放量
     */
    Integer getVideoViewCount(Long videoId);

    /**
     * 获取视频标签
     */
    List<Tag> getVideoTagsByVideoId(Long videoId);
}
