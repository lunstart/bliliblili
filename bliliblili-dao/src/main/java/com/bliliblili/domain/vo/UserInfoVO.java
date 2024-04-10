package com.bliliblili.domain.vo;

import com.bliliblili.domain.entity.UserInfo;
import lombok.Data;

import java.util.Date;

/**
 * @ author 星星草去哪了
 * @ data 2024/3/26 20:50
 * @ 注释
 */
@Data
public class UserInfoVO {
    //用户信息
    private UserInfo userInfo;

    //是否互关 true(是) false(否)
    private Boolean followed;
}
