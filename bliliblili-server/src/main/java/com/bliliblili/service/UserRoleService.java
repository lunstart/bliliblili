package com.bliliblili.service;

import com.bliliblili.domain.auth.UserRole;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 11:39
 * @ 注释
 */
public interface UserRoleService {
    /**
     * 根据用户id查询用户权限角色
     * @param userId
     * @return
     */
    List<UserRole> getUserRoleByUserId(Long userId);
}
