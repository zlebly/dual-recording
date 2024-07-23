package com.georsoft.system.mapper;

import java.util.List;
import com.georsoft.common.core.domain.entity.UsrRole;

/**
 * 角色表 数据层
 * 
 * @author douwenjie
 */
public interface UsrRoleMapper
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<UsrRole> selectRoleList(UsrRole role);

    /**
     * 根据用户ID查询角色
     * 
     * @param id 用户昵称
     * @return 角色列表
     */
    public List<UsrRole> selectRolePermissionByUserId(Long id);

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
    public UsrRole selectRoleById(String roleCode);

    /**
     * 根据用户ID查询角色
     * 
     * @param userName 用户名
     * @return 角色列表
     */
    public List<UsrRole> selectRolesByUserName(String userName);

    /**
     * 校验角色名称是否唯一
     * 
     * @param roleName 角色名称
     * @return 角色信息
     */
    public UsrRole checkRoleNameUnique(String roleName);

    /**
     * 修改角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(UsrRole role);

    /**
     * 新增角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(UsrRole role);

    /**
     * 通过角色ID删除角色
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    public int deleteRoleById(String roleCode);

    /**
     * 批量删除角色信息
     * 
     * @param roleCodes 需要删除的角色ID
     * @return 结果
     */
    public int deleteRoleByIds(String[] roleCodes);
}
