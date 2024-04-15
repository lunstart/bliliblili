package com.bliliblili.service.impl;

import com.bliliblili.domain.auth.AuthRoleElementOperation;
import com.bliliblili.domain.auth.AuthRoleMenu;
import com.bliliblili.domain.auth.UserRole;
import com.bliliblili.domain.vo.UserAuthoritiesVO;
import com.bliliblili.service.AuthRoleService;
import com.bliliblili.service.UserAuthService;
import com.bliliblili.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 11:06
 * @ 注释
 */
@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private AuthRoleService authRoleService;

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    public UserAuthoritiesVO getUserAuthorities(Long userId) {
        List<UserRole> userRoleList = userRoleService.getUserRoleByUserId(userId);
        Set<Long> roleIdSet = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        List<AuthRoleElementOperation> authRoleElementOperations = authRoleService.getRoleElementOperationByRoleIds(roleIdSet);
        List<AuthRoleMenu> authRoleMenus = authRoleService.getAuthRoleMenusByRoleIds(roleIdSet);
        UserAuthoritiesVO userAuthoritiesVO = new UserAuthoritiesVO();
        userAuthoritiesVO.setRoleElementOperationList(authRoleElementOperations);
        userAuthoritiesVO.setRoleMenuList(authRoleMenus);
        return userAuthoritiesVO;
    }
}
