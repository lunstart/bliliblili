package com.bliliblili.service;

import com.bliliblili.domain.entity.UserMoments;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/6 16:32
 * @ 注释
 */
public interface UserMomentService {

    /**
     * 添加动态
     *
     * @param userMoments
     */
    void addUserMoments(UserMoments userMoments) throws Exception;

    /**
     * 获取用户订阅动态
     *
     * @param userId
     * @return
     */
    List<UserMoments> getUserSubscribedMoments(Long userId);
}
