package com.bliliblili.api;

import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/12 17:32
 * @ 注释
 */

@RestController
@Slf4j
@Api(tags = "文件上传接口")
public class FileApi {
    @Autowired
    private FileService fileService;

    @PostMapping("/md5files")
    public JsonResponse<String> getFileMD5(MultipartFile file) throws Exception {
        String fileMD5 = fileService.getFileMD5(file);
        return new JsonResponse<>(fileMD5);
    }

    @PutMapping("/file-slices")
    @ApiOperation("文件分片上传接口")
    public JsonResponse<String> uploadFileBySlices(MultipartFile slice, String fileMd5, Integer sliceNo, Integer totalSliceNo) throws Exception {
        log.info("开始上传文件分片，文件MD5：{}, 分片序号：{}, 总分片数：{}", fileMd5, sliceNo, totalSliceNo);
        String filePath = fileService.uploadFileBySlices(slice, fileMd5, sliceNo, totalSliceNo);
        log.info("文件上传成功，文件路径为：{}", filePath);
        return new JsonResponse<>(filePath);
    }

}
