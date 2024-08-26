package com.bliliblili.service;

import com.bliliblili.domain.dto.VideoCollectionDTO;
import com.bliliblili.domain.entity.Video;
import com.bliliblili.domain.entity.VideoCoin;
import com.bliliblili.domain.entity.VideoComment;
import com.bliliblili.domain.entity.VideoView;
import com.bliliblili.domain.jsonresponse.PageResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/14 19:45
 * @ 注释
 */
public interface VideoService {

    /**
     * 添加视频
     *
     * @param video
     */
    void addVideos(Video video);

    /**
     * 分页查询视频列表
     *
     * @param size 每页视频数
     * @param no   页码
     * @param area 视频分区
     * @return
     */
    PageResult<Video> pageListVideos(Integer size, Integer no, String area);

    /**
     * 视频在线播放
     *
     * @param request
     * @param response
     * @param url
     */
    void viewVideoOnlineBySlices(HttpServletRequest request, HttpServletResponse response, String url) throws Exception;

    /**
     * 视频点赞
     *
     * @param videoId
     * @param userId
     */
    void addVideoLike(Long videoId, Long userId);

    /**
     * 取消视频点赞
     *
     * @param videoId
     * @param userId
     */
    void deleteVideoLike(Long videoId, Long userId);

    /**
     * 获取视频点赞数
     *
     * @param videoId
     * @param userId
     * @return
     */
    Map<String, Object> getVideoLikes(Long videoId, Long userId);

    /**
     * 添加视频收藏
     *
     * @param videoCollectionDTO
     * @param userId
     */
    void addVideoCollection(VideoCollectionDTO videoCollectionDTO, Long userId);

    /**
     * 取消视频收藏
     *
     * @param videoId
     * @param userId
     */
    void deleteVideoCollection(Long videoId, Long userId);

    /**
     * 获取视频收藏数
     *
     * @param videoId
     * @param userId
     * @return
     */
    Map<String, Object> getVideoCollections(Long videoId, Long userId);

    /**
     * 添加视频投币
     *
     * @param videoCoin
     * @param userId
     */
    void addVideoCoins(VideoCoin videoCoin, Long userId);

    /**
     * 查询视频投币
     *
     * @param videoId
     * @param userId
     * @return
     */
    Map<String, Object> getVideoCoins(Long videoId, Long userId);

    /**
     * 添加视频评论
     *
     * @param videoComment
     * @param userId
     */
    void addVideoComment(VideoComment videoComment, Long userId);

    /**
     * 分页查询视频评论
     *
     * @param size
     * @param no
     * @param videoId
     * @return
     */
    PageResult<VideoComment> pageListVideoComments(Integer size, Integer no, Long videoId);

    /**
     * 获取视频详情
     *
     * @param videoId
     * @return
     */
    Map<String, Object> getVideoDetails(Long videoId);

    /**
     * 添加视频播放记录
     *
     * @param videoView
     * @param request
     */
    void addVideoView(VideoView videoView, HttpServletRequest request);

    /**
     * 获取视频播放量
     *
     * @param videoId
     * @return
     */
    Integer getVideoViewCount(Long videoId);
}
