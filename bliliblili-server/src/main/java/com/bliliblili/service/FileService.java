package com.bliliblili.service;

import com.bliliblili.domain.entity.File;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ author 星星草去哪了
 * @ data 2024/5/12 18:38
 * @ 注释
 */
public interface FileService {
    /**
     * 上传文件
     * @param slice
     * @param fileMd5
     * @param sliceNo
     * @param totalSliceNo
     * @return
     * @throws Exception
     */
    String uploadFileBySlices(MultipartFile slice,
                              String fileMd5,
                              Integer sliceNo,
                              Integer totalSliceNo) throws Exception;

    String getFileMD5(MultipartFile file) throws Exception;

    File getFileByMd5(String fileMd5) throws Exception;
}
