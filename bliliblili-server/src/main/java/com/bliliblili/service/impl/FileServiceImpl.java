package com.bliliblili.service.impl;

import com.bliliblili.dao.FileDao;
import com.bliliblili.domain.entity.File;
import com.bliliblili.service.FileService;
import com.bliliblili.service.util.FastDFSUtil;
import com.bliliblili.service.util.MD5Util;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ data 2024/5/12 18:38
 * @ 注释
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao fileDao;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    public String uploadFileBySlices(MultipartFile slice,
                                     String fileMD5,
                                     Integer sliceNo,
                                     Integer totalSliceNo) throws Exception {
        File dbFileMD5 = fileDao.getFileByMD5(fileMD5);
        if(dbFileMD5 != null){
            return dbFileMD5.getUrl();
        }
        String url = fastDFSUtil.uploadFileBySlices(slice, fileMD5, sliceNo, totalSliceNo);
        log.info("文件分片上传地址：{}",url);
        if(!StringUtil.isNullOrEmpty(url)){
            dbFileMD5 = new File();
            dbFileMD5.setCreateTime(new Date());
            dbFileMD5.setMd5(fileMD5);
            dbFileMD5.setUrl(url);
            dbFileMD5.setType(fastDFSUtil.getFileType(slice));
            fileDao.addFile(dbFileMD5);
        }
        return url;
    }

    public String getFileMD5(MultipartFile file) throws Exception {
        return MD5Util.getFileMD5(file);
    }

    public File getFileByMd5(String fileMd5) {
        return fileDao.getFileByMD5(fileMd5);
    }
}
