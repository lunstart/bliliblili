package com.bliliblili.domain.vo;

import com.bliliblili.domain.auth.AuthRoleElementOperation;
import com.bliliblili.domain.auth.AuthRoleMenu;
import lombok.Data;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/4/10 11:10
 * @ 注释 查询用户权限返回的对象
 */
@Data
public class UserAuthoritiesVO {
    //操作权限的相关列表
    List<AuthRoleElementOperation> roleElementOperationList;

    //页面菜单操作权限列表
    List<AuthRoleMenu> roleMenuList;
}
