package com.bliliblili.dao;

import com.bliliblili.domain.entity.File;
import org.apache.ibatis.annotations.Mapper;


/**
 * @ author 星星草去哪了
 * @ data 2024/5/12 18:44
 * @ 注释
 */
@Mapper
public interface FileDao {
    Integer addFile(File file);

    File getFileByMD5(String md5);
}
