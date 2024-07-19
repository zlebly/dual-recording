package com.georsoft.system.service;

import java.util.List;
import java.util.Set;
import com.georsoft.common.core.domain.entity.UsrRole;
import com.georsoft.system.domain.UsrUserRole;

/**
 * 角色业务层
 * 
 * @author douwenjie
 */
public interface IUsrRoleService
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<UsrRole> selectRoleList(UsrRole role);

    /**
     * 根据用户ID查询角色列表
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<UsrRole> selectRolesByUserId(Long userId);

    /**
     * 根据用户ID查询角色权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRolePermissionByUserId(Long userId);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    public List<UsrRole> selectRoleAll();

    /**
     * 根据用户ID获取角色选择框列表
     * 
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    public List<Long> selectRoleListByUserId(Long userId);

    /**
     * 通过角色ID查询角色
     * 
     * @param roleCode 角色ID
     * @return 角色对象信息
     */
    public UsrRole selectRoleById(Long roleCode);

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    public boolean checkRoleNameUnique(UsrRole role);

    /**
     * 校验角色是否允许操作
     * 
     * @param role 角色信息
     */
    public void checkRoleAllowed(UsrRole role);

    /**
     * 校验角色是否有数据权限
     * 
     * @param roleCodes 角色id
     */
    public void checkRoleDataScope(Long... roleCodes);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleCode(Long roleCode);

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(UsrRole role);

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(UsrRole role);

    /**
     * 修改角色状态
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRoleStatus(UsrRole role);

    /**
     * 修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int authDataScope(UsrRole role);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    public int deleteRoleById(Long roleCode);

    /**
     * 批量删除角色信息
     * 
     * @param roleCodes 需要删除的角色ID
     * @return 结果
     */
    public int deleteRoleByIds(Long[] roleCodes);

    /**
     * 取消授权用户角色
     * 
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    public int deleteAuthUser(UsrUserRole userRole);

    /**
     * 批量取消授权用户角色
     * 
     * @param roleCode 角色ID
     * @param userIds 需要取消授权的用户数据ID
     * @return 结果
     */
    public int deleteAuthUsers(Long roleCode, Long[] userIds);

    /**
     * 批量选择授权用户角色
     * 
     * @param roleCode 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int insertAuthUsers(Long roleCode, Long[] userIds);
}
