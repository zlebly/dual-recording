package com.georsoft.system.service.impl;

import java.util.*;

import com.georsoft.system.mapper.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.georsoft.common.annotation.DataScope;
import com.georsoft.common.constant.UserConstants;
import com.georsoft.common.core.domain.entity.UsrRole;
import com.georsoft.common.core.domain.entity.UsrUsers;
import com.georsoft.common.exception.ServiceException;
import com.georsoft.common.utils.SecurityUtils;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.common.utils.spring.SpringUtils;
import com.georsoft.system.domain.orgCode;
import com.georsoft.system.domain.UsrRoleFunction;
import com.georsoft.system.domain.UsrUserRole;
import com.georsoft.system.service.IUsrRoleService;

/**
 * 角色 业务层处理
 * 
 * @author douwenjie
 */
@Service
public class UsrRoleServiceImpl implements IUsrRoleService
{
    @Autowired
    private UsrRoleMapper roleMapper;

    @Autowired
    private UsrRoleFunctionMapper roleFunctionMapper;

    @Autowired
    private UsrUserRoleMapper userRoleMapper;

    @Autowired
    private UsrUserMapper userMapper;

    @Autowired
    private UsrRoleOrgMapper roleOrgMapper;

    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    @DataScope(orgAlias = "d")
    public List<UsrRole> selectRoleList(UsrRole role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<UsrRole> selectRolesByUserId(Long userId)
    {
        List<UsrRole> userRoles = roleMapper.selectRolePermissionByUserId(userId);
        List<UsrRole> roles = selectRoleAll();
        for (UsrRole role : roles)
        {
            for (UsrRole userRole : userRoles)
            {
                if (role.getRoleCode().longValue() == userRole.getRoleCode().longValue())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param id 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long id)
    {
        List<UsrRole> perms = roleMapper.selectRolePermissionByUserId(id);
        Set<String> permsSet = new HashSet<>();
        for (UsrRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                // TODO 角色权限可能待调整
                permsSet.add(Strings.isNotEmpty(perm.getRoleName()) && perm.getRoleName().contains("admin") ? "admin" : "common");
            }
        }
        return permsSet;
    }

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    @Override
    public List<UsrRole> selectRoleAll()
    {
        return SpringUtils.getAopProxy(this).selectRoleList(new UsrRole());
    }

    /**
     * 根据用户ID获取角色选择框列表
     * 
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    @Override
    public List<Long> selectRoleListByUserId(Long userId)
    {
        return roleMapper.selectRoleListByUserId(userId);
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleCode 角色ID
     * @return 角色对象信息
     */
    @Override
    public UsrRole selectRoleById(Long roleCode)
    {
        return roleMapper.selectRoleById(roleCode);
    }

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public boolean checkRoleNameUnique(UsrRole role)
    {
        Long roleCode = StringUtils.isNull(role.getRoleCode()) ? -1L : role.getRoleCode();
        UsrRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleCode().longValue() != roleCode.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     * 
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(UsrRole role)
    {
        if (StringUtils.isNotNull(role.getRoleCode()) && role.isAdmin())
        {
            throw new ServiceException("不允许操作超级管理员角色");
        }
    }

    /**
     * 校验角色是否有数据权限
     * 
     * @param roleCodes 角色id
     */
    @Override
    public void checkRoleDataScope(Long... roleCodes)
    {
        if (!UsrUsers.isAdmin(SecurityUtils.getUsername()))
        {
            for (Long roleCode : roleCodes)
            {
                UsrRole role = new UsrRole();
                role.setRoleCode(roleCode);
                List<UsrRole> roles = SpringUtils.getAopProxy(this).selectRoleList(role);
                if (StringUtils.isEmpty(roles))
                {
                    throw new ServiceException("没有权限访问角色数据！");
                }
            }
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleCode(Long roleCode)
    {
        return userRoleMapper.countUserRoleByRoleCode(roleCode);
    }

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(UsrRole role)
    {
        // 新增角色信息
        roleMapper.insertRole(role);
        return insertRoleFunction(role);
    }

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(UsrRole role)
    {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与菜单关联
        roleFunctionMapper.deleteRoleFunctionByRoleCode(role.getRoleCode());
        return insertRoleFunction(role);
    }

    /**
     * 修改角色状态
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRoleStatus(UsrRole role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * 修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int authDataScope(UsrRole role)
    {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与部门关联
        roleOrgMapper.deleteRoleOrgByRoleCode(role.getRoleCode());
        // 新增角色和部门信息（数据权限）
        return insertRoleOrg(role);
    }

    /**
     * 新增角色菜单信息
     * 
     * @param role 角色对象
     */
    public int insertRoleFunction(UsrRole role)
    {
        int rows = 1;
        // 新增用户与角色管理
        List<UsrRoleFunction> list = new ArrayList<UsrRoleFunction>();
        for (Long functionCode : role.getFunctionCodes())
        {
            UsrRoleFunction rm = new UsrRoleFunction();
            rm.setRoleCode(role.getRoleCode());
            rm.setFunctionCode(functionCode);
            list.add(rm);
        }
        if (list.size() > 0)
        {
            rows = roleFunctionMapper.batchRoleFunction(list);
        }
        return rows;
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleOrg(UsrRole role)
    {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<orgCode> list = new ArrayList<orgCode>();
        for (Long orgId : role.getOrgCodes())
        {
            orgCode rd = new orgCode();
            rd.setRoleCode(role.getRoleCode());
            rd.setOrgCode(orgId);
            list.add(rd);
        }
        if (list.size() > 0)
        {
            rows = roleOrgMapper.batchRoleOrg(list);
        }
        return rows;
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteRoleById(Long roleCode)
    {
        // 删除角色与菜单关联
        roleFunctionMapper.deleteRoleFunctionByRoleCode(roleCode);
        // 删除角色与部门关联
        roleOrgMapper.deleteRoleOrgByRoleCode(roleCode);
        return roleMapper.deleteRoleById(roleCode);
    }

    /**
     * 批量删除角色信息
     * 
     * @param roleCodes 需要删除的角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteRoleByIds(Long[] roleCodes)
    {
        for (Long roleCode : roleCodes)
        {
            checkRoleAllowed(new UsrRole(roleCode));
            checkRoleDataScope(roleCode);
            UsrRole role = selectRoleById(roleCode);
            if (countUserRoleByRoleCode(roleCode) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        // 删除角色与菜单关联
        roleFunctionMapper.deleteRoleFunction(roleCodes);
        // 删除角色与部门关联
        roleOrgMapper.deleteRoleOrg(roleCodes);
        return roleMapper.deleteRoleByIds(roleCodes);
    }

    /**
     * 取消授权用户角色
     * 
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public int deleteAuthUser(UsrUserRole userRole)
    {
        return userRoleMapper.deleteUserRoleInfo(userRole);
    }

    /**
     * 批量取消授权用户角色
     * 
     * @param roleCode 角色ID
     * @param userIds 需要取消授权的用户数据ID
     * @return 结果
     */
    @Override
    public int deleteAuthUsers(Long roleCode, Long[] userIds)
    {
        return userRoleMapper.deleteUserRoleInfos(roleCode, userIds);
    }

    /**
     * 批量选择授权用户角色
     * 
     * @param roleCode 角色ID
     * @param userIds 需要授权的用户数据ID
     * @return 结果
     */
    @Override
    public int insertAuthUsers(Long roleCode, Long[] userIds)
    {
        // 新增用户与角色管理
        List<UsrUserRole> list = new ArrayList<UsrUserRole>();
        for (Long userId : userIds)
        {
            UsrUserRole ur = new UsrUserRole();
            ur.setLoginName(userMapper.selectLoginNameByUserId(userId));
            ur.setRoleCode(roleCode);
            list.add(ur);
        }
        return userRoleMapper.batchUserRole(list);
    }
}
