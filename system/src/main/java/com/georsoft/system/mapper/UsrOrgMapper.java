package com.georsoft.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.georsoft.common.core.domain.entity.UsrOrg;

/**
 * 机构管理 数据层
 * 
 * @author douwenjie
 */
public interface UsrOrgMapper
{
    /**
     * 查询机构管理数据
     * 
     * @param org 机构信息
     * @return 机构信息集合
     */
    public List<UsrOrg> selectOrgList(UsrOrg org);

    /**
     * 根据角色ID查询机构树信息
     * 
     * @param roleCode 角色ID
     * @return 选中机构列表
     */
    public List<Long> selectOrgListByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 根据机构码查询信息
     * 
     * @param orgCode 机构码
     * @return 机构信息
     */
    public UsrOrg selectOrgById(String orgCode);

    /**
     * 根据ID查询所有子机构（正常状态）
     * 
     * @param orgCode 机构码
     * @return 子机构数
     */
    public int selectNormalChildrenOrgById(String orgCode);

    /**
     * 是否存在子节点
     * 
     * @param orgCode 机构码
     * @return 结果
     */
    public int hasChildByOrgCode(String orgCode);

    /**
     * 查询机构是否存在用户
     * 
     * @param orgCode 机构码
     * @return 结果
     */
    public int checkOrgExistUser(String orgCode);

    /**
     * 校验机构名称是否唯一
     * 
     * @param orgName 机构名称
     * @param parentOrgCode 父机构码
     * @return 结果
     */
    public UsrOrg checkOrgNameUnique(@Param("orgName") String orgName, @Param("parentOrgCode") String parentOrgCode);

    /**
     * 新增机构信息
     * 
     * @param org 机构信息
     * @return 结果
     */
    public int insertOrg(UsrOrg org);

    /**
     * 修改机构信息
     * 
     * @param org 机构信息
     * @return 结果
     */
    public int updateOrg(UsrOrg org);

    /**
     * 修改所在机构正常状态
     * 
     * @param orgCodes 机构码组
     */
    public void updateOrgStatusNormal(String[] orgCodes);

    /**
     * 修改子元素关系
     * 
     * @param orgs 子元素
     * @return 结果
     */
    public int updateOrgChildren(@Param("orgs") List<UsrOrg> orgs);

    /**
     * 删除机构管理信息
     * 
     * @param orgCode 机构码
     * @return 结果
     */
    public int deleteOrgById(String orgCode);
}
