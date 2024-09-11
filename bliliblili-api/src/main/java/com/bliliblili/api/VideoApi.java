package com.bliliblili.api;

import com.bliliblili.api.support.UserSupport;
//import com.bliliblili.dao.repository.VideoRepository;
import com.bliliblili.domain.dto.VideoCollectionDTO;
import com.bliliblili.domain.entity.Video;
import com.bliliblili.domain.entity.VideoCoin;
import com.bliliblili.domain.entity.VideoComment;
import com.bliliblili.domain.entity.VideoView;
import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.domain.jsonresponse.PageResult;
//import com.bliliblili.service.ElasticSearchService;
import com.bliliblili.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;
import java.util.Map;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/14 19:44
 * @ 注释
 */
@RestController
@Slf4j
@Api(tags = "视频相关接口")
public class VideoApi {

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserSupport userSupport;

//    @Autowired
//    private ElasticSearchService elasticSearchService;

    /**
     * 视频投稿
     *
     * @param video
     * @return
     */
    @PostMapping("/videos")
    @ApiOperation("视频投稿接口")
    public JsonResponse<String> addVideos(@RequestBody Video video) {
        Long userId = userSupport.getCurrentUserId();
        log.info("用户视频投稿:{}", userId + "\n" + video.toString());
        video.setUserId(userId);
        //添加视频到数据库
        videoService.addVideos(video);
        //添加视频到es
        //elasticSearchService.addVideo(video);
        return JsonResponse.success();
    }

    /**
     * 视频分页查询
     *
     * @return
     */
    @GetMapping("/videos")
    @ApiOperation("视频分页查询接口")
    public JsonResponse<PageResult<Video>> pageListVideos(Integer size, Integer no, String area) {
        log.info("视频分页查询接口,size:{},no:{},area:{}", size, no, area);
        PageResult<Video> result = videoService.pageListVideos(size, no, area);
        return new JsonResponse<>(result);
    }

    /**
     * 视频在线播放
     *
     * @param request
     * @param response
     * @param url
     * @throws Exception
     */
    @GetMapping("/video-slice")
    @ApiOperation("视频在线播放")
    public void viewVideoOnlineBySlices(HttpServletRequest request, HttpServletResponse response, String url) throws Exception {
        videoService.viewVideoOnlineBySlices(request, response, url);
    }


    /**
     * 视频点赞
     *
     * @param videoId
     * @return
     */
    @PostMapping("/video-likes")
    @ApiOperation("点赞视频接口")
    public JsonResponse<String> addVideoLike(@RequestParam Long videoId) {
        Long userId = userSupport.getCurrentUserId();
        videoService.addVideoLike(videoId, userId);
        return JsonResponse.success();
    }

    /**
     * 取消视频点赞
     *
     * @param videoId
     * @return
     */
    @DeleteMapping("/video-likes")
    @ApiOperation("取消点赞视频接口")
    public JsonResponse<String> deleteVideoLike(@RequestParam Long videoId) {
        Long userId = userSupport.getCurrentUserId();
        videoService.deleteVideoLike(videoId, userId);
        return JsonResponse.success();
    }

    /**
     * 查询视频点赞数量
     *
     * @param videoId
     * @return
     */
    @GetMapping("/video-likes")
    @ApiOperation("查询视频点赞数量接口")
    public JsonResponse<Map<String, Object>> getVideoLikes(@RequestParam Long videoId) {
        Long userId = null;
        try {
            userId = userSupport.getCurrentUserId();
        } catch (Exception ignored) {
        }
        Map<String, Object> result = videoService.getVideoLikes(videoId, userId);
        return new JsonResponse<>(result);
    }

    /**
     * 收藏视频
     *
     * @param videoCollectionDTO
     * @return
     */
    @PostMapping("/video-collections")
    @ApiOperation("收藏视频接口")
    public JsonResponse<String> addVideoCollection(@RequestBody VideoCollectionDTO videoCollectionDTO) {
        Long userId = userSupport.getCurrentUserId();
        videoService.addVideoCollection(videoCollectionDTO, userId);
        return JsonResponse.success();
    }

    /**
     * 取消视频收藏
     *
     * @param videoId
     * @return
     */
    @DeleteMapping("/video-collections")
    @ApiOperation("取消视频收藏接口")
    public JsonResponse<String> deleteVideoCollection(@RequestParam Long videoId) {
        Long userId = userSupport.getCurrentUserId();
        videoService.deleteVideoCollection(videoId, userId);
        return JsonResponse.success();
    }

    /**
     * 查询视频收藏数量
     *
     * @param videoId
     * @return
     */
    @GetMapping("/video-collections")
    @ApiOperation("查询视频收藏数量接口")
    public JsonResponse<Map<String, Object>> getVideoCollections(@RequestParam Long videoId) {
        Long userId = null;
        try {
            userId = userSupport.getCurrentUserId();
        } catch (Exception ignored) {
        }
        Map<String, Object> result = videoService.getVideoCollections(videoId, userId);
        return new JsonResponse<>(result);
    }

    /**
     * 视频投币
     *
     * @param videoCoin
     * @return
     */
    @PostMapping("/video-coins")
    @ApiOperation("视频投币接口")
    public JsonResponse<String> addVideoCoins(@RequestBody VideoCoin videoCoin) {
        Long userId = userSupport.getCurrentUserId();
        videoService.addVideoCoins(videoCoin, userId);
        return JsonResponse.success();
    }

    /**
     * 查询视频投币数量
     *
     * @param videoId
     * @return
     */
    @GetMapping("/video-coins")
    @ApiOperation("查询视频投币数量接口")
    public JsonResponse<Map<String, Object>> getVideoCoins(@RequestParam Long videoId) {
        Long userId = null;
        try {
            userId = userSupport.getCurrentUserId();
        } catch (Exception ignored) {
        }
        Map<String, Object> result = videoService.getVideoCoins(videoId, userId);
        return new JsonResponse<>(result);
    }

    /**
     * 添加视频评论
     *
     * @param videoComment
     * @return
     */
    @PostMapping("/video-comments")
    @ApiOperation("添加视频评论接口")
    public JsonResponse<String> addVideoComment(@RequestBody VideoComment videoComment) {
        Long userId = userSupport.getCurrentUserId();
        videoService.addVideoComment(videoComment, userId);
        return JsonResponse.success();
    }


    /**
     * 分页查询视频评论
     *
     * @param size
     * @param no
     * @param videoId
     * @return
     */
    @GetMapping("/video-comments")
    @ApiOperation("分页查询视频评论接口")
    public JsonResponse<PageResult<VideoComment>> pageListVideoComments(@RequestParam Integer size, @RequestParam Integer no, @RequestParam Long videoId) {
        PageResult<VideoComment> result = videoService.pageListVideoComments(size, no, videoId);
        return new JsonResponse<>(result);
    }

    /**
     * 获取视频详情
     *
     * @param videoId
     * @return
     */
    @GetMapping("/video-details")
    @ApiOperation("获取视频详情接口")
    public JsonResponse<Map<String, Object>> getVideoDetails(@RequestParam Long videoId) {
        Map<String, Object> result = videoService.getVideoDetails(videoId);
        return new JsonResponse<>(result);
    }

    @PostMapping("/video-views")
    @ApiOperation("添加视频播放记录接口")
    public JsonResponse<String> addVideoView(@RequestBody VideoView videoView, HttpServletRequest request){
        log.info("添加视频播放记录接口:{}",videoView.toString());
        Long userId;
        try{
            userId = userSupport.getCurrentUserId();
            videoView.setUserId(userId);
            videoService.addVideoView(videoView,request);
        }catch(Exception e){
            videoService.addVideoView(videoView,request);
        }
        return JsonResponse.success();
    }

    @GetMapping("/video-view-counts")
    @ApiOperation("获取视频播放数量接口")
    public JsonResponse<Integer> getVideoViewCount(@RequestParam Long videoId){
        Integer count = videoService.getVideoViewCount(videoId);
        return new JsonResponse<>(count);
    }
}
