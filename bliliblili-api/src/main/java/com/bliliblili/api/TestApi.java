package com.bliliblili.api;


import com.bliliblili.domain.entity.Video;
import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.service.ElasticSearchService;
import com.bliliblili.service.util.FastDFSUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class TestApi {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes = "测试接口")
    public String test(){
        return "jrebol";
    }

    @GetMapping("/slices")
    @ApiOperation(value = "切片上传", notes = "切片上传")
    public JsonResponse<String> slices(MultipartFile file) throws Exception {
        log.info("开始切片");
        fastDFSUtil.convertFileToSlices(file);
        return JsonResponse.success("切片成功");
    }

    @GetMapping("/es-videos")
    @ApiOperation(value = "搜索视频", notes = "搜索视频")
    public JsonResponse<Video> geEesVideos(@RequestParam String keyword) {
        Video video = elasticSearchService.getVideos(keyword);
        return new JsonResponse<>(video);
    }
}
