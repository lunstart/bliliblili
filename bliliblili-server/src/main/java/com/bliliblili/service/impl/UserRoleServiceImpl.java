package com.bliliblili.service.impl;

import com.bliliblili.dao.UserRoleDao;
import com.bliliblili.domain.auth.UserRole;
import com.bliliblili.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 18:33
 * @ 注释
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;


    /**
     * 根据用户id查询用户权限角色
     *
     * @param userId
     * @return
     */
    public List<UserRole> getUserRoleByUserId(Long userId) {
        return userRoleDao.getUserRoleByUserId(userId);
    }
}
