package com.bliliblili.api;

import com.bliliblili.domain.jsonresponse.JsonResponse;
import com.bliliblili.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
    @ApiOperation("获取文件MD5")
    public JsonResponse<String> getFileMD5(MultipartFile file) throws Exception {
        String fileMD5 = fileService.getFileMD5(file);
        return new JsonResponse<>(fileMD5);
    }

    @PostMapping("file")
    @ApiOperation("文件上传")
    public JsonResponse<String> upload(MultipartFile file, String fileMD5) throws Exception {
        log.info("开始上传");
        String url = fileService.uploadFile(file, fileMD5);
        log.info("上传成功，url={}", url);
        return JsonResponse.success(url);
    }

    @PutMapping("/file-slices")
    @ApiOperation("文件分片上传接口")
    public JsonResponse<String> uploadFileBySlices(MultipartFile slice, String fileMd5, Integer sliceNo, Integer totalSliceNo) throws Exception {
        log.info("开始上传文件分片，文件MD5：{}, 分片序号：{}, 总分片数：{}", fileMd5, sliceNo, totalSliceNo);
        String filePath = fileService.uploadFileBySlices(slice, fileMd5, sliceNo, totalSliceNo);
        log.info("文件上传成功，文件路径为：{}", filePath);
        return new JsonResponse<>(filePath);
//        String path = "D:\\Temp\\fileTest\\" + sliceNo + "-"+ totalSliceNo+"." + "mp4";
//        slice.transferTo(new File(path));
//        return new JsonResponse<>("111");
    }

    @DeleteMapping("/file-delete")
    @ApiOperation("文件删除接口")
    public JsonResponse<String> deleteFile(String filePath) {
        log.info("开始删除文件，文件路径：{}", filePath);
        fileService.deleteFile(filePath);
        log.info("文件删除成功");
        return JsonResponse.success();
    }
}
