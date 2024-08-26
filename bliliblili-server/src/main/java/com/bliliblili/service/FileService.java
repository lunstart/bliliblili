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
     *
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

    /**
     * 获取文件MD5
     *
     * @param file
     * @return
     * @throws Exception
     */
    String getFileMD5(MultipartFile file) throws Exception;

    /**
     * 通过MD5获取文件
     * @param fileMd5
     * @return
     * @throws Exception
     */

    File getFileByMd5(String fileMd5) throws Exception;

    /**
     * 通过文件路径删除文件
     * @param filePath
     */
    void deleteFile(String filePath);

    /**
     * 上传文件
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file,String fileMd5) throws Exception;

    /**
     * 根据url获取文件名
     * @param url
     * @return
     */
    String getFileNameByUrl(String url);
}
