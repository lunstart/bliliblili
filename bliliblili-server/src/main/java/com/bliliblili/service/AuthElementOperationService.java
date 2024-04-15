package com.bliliblili.service;

import com.bliliblili.domain.auth.AuthRoleElementOperation;

import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/15 14:19
 * @ 注释
 */
public interface AuthElementOperationService {

    /**
     * 根据角色id集合获取权限
     * @param roleIdSet
     * @return
     */
    List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(Set<Long> roleIdSet);
}
