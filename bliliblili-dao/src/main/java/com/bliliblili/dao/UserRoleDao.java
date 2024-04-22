package com.bliliblili.dao;

import com.bliliblili.domain.auth.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 19:42
 * @ 注释
 */
@Mapper
public interface UserRoleDao {
    List<UserRole> getUserRoleByUserId(Long userId);

    void addUserRole(UserRole userRole);
}
