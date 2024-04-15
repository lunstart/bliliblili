package com.bliliblili.service;

import com.bliliblili.domain.auth.AuthRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/15 14:21
 * @ 注释
 */
public interface AuthRoleMenuService {
    /**
     * @param roleIdSet
     * @return
     */
    List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet);
}
