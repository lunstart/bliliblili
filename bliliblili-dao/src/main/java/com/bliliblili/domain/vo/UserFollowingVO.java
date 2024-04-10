package com.bliliblili.domain.vo;

import com.bliliblili.domain.entity.UserFollowing;
import com.bliliblili.domain.entity.UserInfo;
import lombok.Data;

import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ date 2024/3/26 19:19
 * @ 注释
 */
@Data
public class UserFollowingVO {
    //用户关注
    private UserFollowing userFollowing;
    //用户信息
    private UserInfo userInfo;

}
