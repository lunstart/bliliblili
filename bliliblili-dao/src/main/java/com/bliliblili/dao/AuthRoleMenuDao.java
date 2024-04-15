package com.bliliblili.dao;

import com.bliliblili.domain.auth.AuthRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/15 15:15
 * @ 注释
 */
@Mapper
public interface AuthRoleMenuDao {

    List<AuthRoleMenu> getAuthRoleMenusByRoleIds(Set<Long> roleIdSet);
}
