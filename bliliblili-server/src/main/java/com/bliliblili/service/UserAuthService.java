package com.bliliblili.service;

import com.bliliblili.domain.vo.UserAuthoritiesVO;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 11:06
 * @ 注释
 */
public interface UserAuthService {
    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    UserAuthoritiesVO getUserAuthorities(Long userId);
}
