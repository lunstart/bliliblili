package com.bliliblili.dao;

import com.bliliblili.domain.entity.UserMoments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/6 16:49
 * @ 注释
 */
@Mapper
public interface UserMomentsDao {

    Integer addUserMoments(UserMoments userMoments);

    List<UserMoments> getUserSubscribedMoments(Long userId);
}
