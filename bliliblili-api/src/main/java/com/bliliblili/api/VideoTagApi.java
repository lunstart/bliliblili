package com.bliliblili.api;


import com.bliliblili.api.support.UserSupport;
import com.bliliblili.domain.entity.Tag;
import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.service.VideoTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ author ljw
 * @ data 2024/9/12 15:40
 */

@RestController
@Slf4j
@Api(tags = "视频标签想相关接口")
public class VideoTagApi {

    @Autowired
    private VideoTagService videoTagService;

    @Autowired
    private UserSupport userSupport;

    @PostMapping("/tags")
    @ApiOperation("添加视频标签")
    JsonResponse<Long> addVideoTag(@RequestBody Tag tag){
        Long userId = userSupport.getCurrentUserId();
        log.info("添加视频标签 tag:{}",tag);
        Long tagId = videoTagService.addVideoTag(tag);
        return new JsonResponse<>(tagId);
    }

    @DeleteMapping("/tags")
    @ApiOperation("删除视频标签")
    JsonResponse<String> deleteVideoTag(@RequestParam Long tagId){
        Long userId = userSupport.getCurrentUserId();
        log.info("删除视频标签 tagId:{}",tagId);
        videoTagService.deleteVideoTag(tagId);
        return JsonResponse.success();
    }
}
