package com.georsoft.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.georsoft.system.domain.UsrUserRole;

/**
 * 用户与角色关联表 数据层
 * 
 * @author douwenjie
 */
public interface UsrUserRoleMapper
{
    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserRole(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleCode(Long roleCode);

    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<UsrUserRole> userRoleList);

    /**
     * 删除用户和角色关联信息
     * 
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    public int deleteUserRoleInfo(UsrUserRole userRole);

    /**
     * 批量取消授权用户角色
     * 
     * @param roleCode 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteUserRoleInfos(@Param("roleCode") Long roleCode, @Param("userIds") Long[] userIds);
}
