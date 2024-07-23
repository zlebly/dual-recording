package com.georsoft.system.mapper;

import java.util.List;
import com.georsoft.system.domain.orgCode;

/**
 * 角色与机构关联表 数据层
 * 
 * @author douwenjie
 */
public interface UsrRoleOrgMapper
{
    /**
     * 通过角色ID删除角色和机构关联
     * 
     * @param roleCode 角色ID
     * @return 结果
     */
    public int deleteRoleOrgByRoleCode(String roleCode);

    /**
     * 批量删除角色机构关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleOrg(String[] ids);

    /**
     * 查询机构使用数量
     * 
     * @param orgCode 机构码
     * @return 结果
     */
    public int selectCountRoleOrgByOrgCode(String orgCode);

    /**
     * 批量新增角色机构信息
     * 
     * @param roleOrgList 角色机构列表
     * @return 结果
     */
    public int batchRoleOrg(List<orgCode> roleOrgList);
}
