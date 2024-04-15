package com.bliliblili.service.impl;

import com.bliliblili.dao.AuthRoleMenuDao;
import com.bliliblili.domain.auth.AuthRoleMenu;
import com.bliliblili.service.AuthRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/15 14:21
 * @ 注释
 */
@Service
public class AuthRoleMenuServiceImpl implements AuthRoleMenuService {
    @Autowired
    private AuthRoleMenuDao authRoleMenuDao;

    /**
     *
     * @param roleIdSet
     * @return
     */
    public List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet) {
        return authRoleMenuDao.getAuthRoleMenusByRoleIds(roleIdSet);
    }
}
