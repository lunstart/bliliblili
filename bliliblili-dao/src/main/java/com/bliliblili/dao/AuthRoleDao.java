package com.bliliblili.dao;

import com.bliliblili.domain.auth.AuthRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/19 17:34
 * @ 注释
 */
@Mapper
public interface AuthRoleDao {

    AuthRole getRoleByCode(String code);
}
