package com.georsoft.system.service;

import java.util.List;
import com.georsoft.common.core.domain.TreeSelect;
import com.georsoft.common.core.domain.entity.UsrOrg;

/**
 * 机构管理 服务层
 * 
 * @author douwenjie
 */
public interface IUsrOrgService
{
    /**
     * 查询机构管理数据
     * 
     * @param org 机构信息
     * @return 机构信息集合
     */
    public List<UsrOrg> selectOrgList(UsrOrg org);

    /**
     * 查询机构树结构信息
     * 
     * @param org 机构信息
     * @return 机构树信息集合
     */
    public List<TreeSelect> selectOrgTreeList(UsrOrg org);

    /**
     * 构建前端所需要树结构
     * 
     * @param orgs 机构列表
     * @return 树结构列表
     */
    public List<UsrOrg> buildOrgTree(List<UsrOrg> orgs);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param orgs 机构列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildOrgTreeSelect(List<UsrOrg> orgs);

    /**
     * 根据角色ID查询机构树信息
     * 
     * @param roleId 角色ID
     * @return 选中机构列表
     */
    public List<Long> selectOrgListByRoleId(Long roleId);

    /**
     * 根据机构码查询信息
     * 
     * @param orgCode 机构码
     * @return 机构信息
     */
    public UsrOrg selectOrgById(Long orgCode);

    /**
     * 根据ID查询所有子机构（正常状态）
     * 
     * @param orgCode 机构码
     * @return 子机构数
     */
    public int selectNormalChildrenOrgById(Long orgCode);

    /**
     * 是否存在机构子节点
     * 
     * @param orgCode 机构码
     * @return 结果
     */
    public boolean hasChildByOrgCode(Long orgCode);

    /**
     * 查询机构是否存在用户
     * 
     * @param orgCode 机构码
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkOrgExistUser(Long orgCode);

    /**
     * 校验机构名称是否唯一
     * 
     * @param org 机构信息
     * @return 结果
     */
    public boolean checkOrgNameUnique(UsrOrg org);

    /**
     * 校验机构是否有数据权限
     * 
     * @param orgCode 机构码
     */
    public void checkOrgDataScope(Long orgCode);

    /**
     * 新增保存机构信息
     * 
     * @param org 机构信息
     * @return 结果
     */
    public int insertOrg(UsrOrg org);

    /**
     * 修改保存机构信息
     * 
     * @param org 机构信息
     * @return 结果
     */
    public int updateOrg(UsrOrg org);

    /**
     * 删除机构管理信息
     * 
     * @param orgCode 机构码
     * @return 结果
     */
    public int deleteOrgById(Long orgCode);
}
