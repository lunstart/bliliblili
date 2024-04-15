package com.bliliblili.service.impl;

import com.bliliblili.domain.auth.AuthElementOperation;
import com.bliliblili.domain.auth.AuthMenu;
import com.bliliblili.domain.auth.AuthRoleElementOperation;
import com.bliliblili.domain.auth.AuthRoleMenu;
import com.bliliblili.service.AuthElementOperationService;
import com.bliliblili.service.AuthRoleMenuService;
import com.bliliblili.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 18:33
 * @ 注释
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {
    @Autowired
    private AuthElementOperationService authElementOperationService;

    @Autowired
    private AuthRoleMenuService authRoleMenuService;

    /**
     * 根据角色id集合获取角色与元素操作权限
     *
     * @param roleIdSet
     * @return
     */
    public List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(Set<Long> roleIdSet) {
        return authElementOperationService.getRoleElementOperationByRoleIds(roleIdSet);
    }

    /**
     * 根据角色id集合获取角色与菜单权限
     * @param roleIdSet
     * @return
     */
    public List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet) {
        return authRoleMenuService.getAuthRoleMenusByRoleIds(roleIdSet);
    }
}
