package com.bliliblili.service.impl;

import com.bliliblili.dao.AuthElementOperationDao;
import com.bliliblili.domain.auth.AuthRoleElementOperation;
import com.bliliblili.service.AuthElementOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/15 14:22
 * @ 注释
 */
@Service
public class AuthElementOperationServiceImpl implements AuthElementOperationService {
    @Autowired
    private AuthElementOperationDao authElementOperationDao;
    /**
     * 根据角色id集合获取权限
     *
     * @param roleIdSet
     * @return
     */
    public List<AuthRoleElementOperation> getRoleElementOperationByRoleIds(Set<Long> roleIdSet) {
        return authElementOperationDao.getRoleElementOperationByRoleIds(roleIdSet);
    }
}
