package com.bliliblili.api;


import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.service.util.FastDFSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @GetMapping("/test")
    public String test(){
        return "jrebol";
    }

    @GetMapping("/slices")
    public JsonResponse<String> slices(MultipartFile file) throws Exception {
        log.info("开始切片");
        fastDFSUtil.convertFileToSlices(file);
        return JsonResponse.success("切片成功");
    }


}
