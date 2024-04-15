package com.bliliblili.service;

import com.bliliblili.domain.auth.AuthRoleElementOperation;
import com.bliliblili.domain.auth.AuthRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 11:39
 * @ 注释
 */
public interface AuthRoleService {


    /**
     * 获取角色元素权限
     *
     * @param roleIdSet
     * @return
     */
    List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(Set<Long> roleIdSet);

    /**
     * 获取控制角色页面菜单权限
     * @param roleIdSet
     * @return
     */
    List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet);
}
