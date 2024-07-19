package com.georsoft.framework.web.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.georsoft.common.core.domain.entity.UsrRole;
import com.georsoft.common.core.domain.entity.UsrUsers;
import com.georsoft.system.service.IUsrFunctionTreeService;
import com.georsoft.system.service.IUsrRoleService;

/**
 * 用户权限处理
 * 
 * @author douwenjie
 */
@Component
public class SysPermissionService
{
    @Autowired
    private IUsrRoleService roleService;

    @Autowired
    private IUsrFunctionTreeService functionService;

    /**
     * 获取角色数据权限
     * 
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(UsrUsers user)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getFunctionPermission(UsrUsers user)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            List<UsrRole> roles = user.getRoles();
            if (!CollectionUtils.isEmpty(roles))
            {
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (UsrRole role : roles)
                {
                    Set<String> rolePerms = functionService.selectFunctionPermsByRoleCode(role.getRoleCode());
                    role.setPermissions(rolePerms);
                    perms.addAll(rolePerms);
                }
            }
            else
            {
                perms.addAll(functionService.selectFunctionPermsByUserId(user.getLoginName()));
            }
        }
        return perms;
    }
}
