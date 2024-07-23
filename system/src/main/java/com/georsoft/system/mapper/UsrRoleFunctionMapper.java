package com.georsoft.system.mapper;

import java.util.List;
import com.georsoft.system.domain.UsrRoleFunction;

/**
 * 角色与菜单关联表 数据层
 * 
 * @author douwenjie
 */
public interface UsrRoleFunctionMapper
{
    /**
     * 查询菜单使用数量
     * 
     * @param functionCode 菜单ID
     * @return 结果
     */
    public int checkFunctionExistRole(String functionCode);

    /**
     * 通过角色ID删除角色和菜单关联
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    public int deleteRoleFunctionByRoleCode(String roleCode);

    /**
     * 批量删除角色菜单关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleFunction(String[] ids);

    /**
     * 批量新增角色菜单信息
     * 
     * @param roleFunctionList 角色菜单列表
     * @return 结果
     */
    public int batchRoleFunction(List<UsrRoleFunction> roleFunctionList);
}
