package com.bliliblili.domain.vo;

import com.bliliblili.domain.entity.UserFollowing;
import com.bliliblili.domain.entity.UserInfo;
import lombok.Data;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/27 8:36
 * @ 注释
 */
@Data
public class FollowingVO {
    //用户关注
    private UserFollowing userFollowing;
    //用户信息
    private UserInfo userInfo;
    //是否互关 true(是) false(否)
    private Boolean followed;
}
