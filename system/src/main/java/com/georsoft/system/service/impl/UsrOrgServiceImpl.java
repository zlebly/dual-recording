package com.georsoft.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.georsoft.common.annotation.DataScope;
import com.georsoft.common.constant.UserConstants;
import com.georsoft.common.core.domain.TreeSelect;
import com.georsoft.common.core.domain.entity.UsrOrg;
import com.georsoft.common.core.domain.entity.UsrRole;
import com.georsoft.common.core.domain.entity.UsrUsers;
import com.georsoft.common.core.text.Convert;
import com.georsoft.common.exception.ServiceException;
import com.georsoft.common.utils.SecurityUtils;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.common.utils.spring.SpringUtils;
import com.georsoft.system.mapper.UsrOrgMapper;
import com.georsoft.system.mapper.UsrRoleMapper;
import com.georsoft.system.service.IUsrOrgService;

/**
 *机构管理 服务实现
 * 
 * @author douwenjie
 */
@Service
public class UsrOrgServiceImpl implements IUsrOrgService
{
    @Autowired
    private UsrOrgMapper orgMapper;

    @Autowired
    private UsrRoleMapper roleMapper;

    /**
     * 查询机构管理数据
     * 
     * @param org 机构信息
     * @return 机构信息集合
     */
    @Override
    @DataScope(orgAlias = "d")
    public List<UsrOrg> selectOrgList(UsrOrg org)
    {
        return orgMapper.selectOrgList(org);
    }

    /**
     * 查询机构树结构信息
     * 
     * @param org 机构信息
     * @return 机构树信息集合
     */
    @Override
    public List<TreeSelect> selectOrgTreeList(UsrOrg org)
    {
        List<UsrOrg> orgs = SpringUtils.getAopProxy(this).selectOrgList(org);
        return buildOrgTreeSelect(orgs);
    }

    /**
     * 构建前端所需要树结构
     * 
     * @param orgs 机构列表
     * @return 树结构列表
     */
    @Override
    public List<UsrOrg> buildOrgTree(List<UsrOrg> orgs)
    {
        List<UsrOrg> returnList = new ArrayList<UsrOrg>();
        List<Long> tempList = orgs.stream().map(UsrOrg::getOrgCode).collect(Collectors.toList());
        for (UsrOrg org : orgs)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(org.getParentOrgCode()))
            {
                recursionFn(orgs, org);
                returnList.add(org);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = orgs;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param orgs 机构列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildOrgTreeSelect(List<UsrOrg> orgs)
    {
        List<UsrOrg> orgTrees = buildOrgTree(orgs);
        return orgTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据角色ID查询机构树信息
     * 
     * @param roleId 角色ID
     * @return 选中机构列表
     */
    @Override
    public List<Long> selectOrgListByRoleId(Long roleId)
    {
//        UsrRole role = roleMapper.selectRoleById(roleId);
        return orgMapper.selectOrgListByRoleId(roleId);
    }

    /**
     * 根据机构ID查询信息
     * 
     * @param orgCode 机构ID
     * @return 机构信息
     */
    @Override
    public UsrOrg selectOrgById(Long orgCode)
    {
        return orgMapper.selectOrgById(orgCode);
    }

    /**
     * 根据ID查询所有子机构（正常状态）
     * 
     * @param orgId 机构ID
     * @return 子机构数
     */
    @Override
    public int selectNormalChildrenOrgById(Long orgId)
    {
        return orgMapper.selectNormalChildrenOrgById(orgId);
    }

    /**
     * 是否存在子节点
     * 
     * @param orgCode 机构ID
     * @return 结果
     */
    @Override
    public boolean hasChildByOrgCode(Long orgCode)
    {
        int result = orgMapper.hasChildByOrgCode(orgCode);
        return result > 0;
    }

    /**
     * 查询机构是否存在用户
     * 
     * @param orgCode 机构ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkOrgExistUser(Long orgCode)
    {
        int result = orgMapper.checkOrgExistUser(orgCode);
        return result > 0;
    }

    /**
     * 校验机构名称是否唯一
     * 
     * @param org 机构信息
     * @return 结果
     */
    @Override
    public boolean checkOrgNameUnique(UsrOrg org)
    {
        Long orgCode = StringUtils.isNull(org.getOrgCode()) ? -1L : org.getOrgCode();
        UsrOrg info = orgMapper.checkOrgNameUnique(org.getOrgName(), org.getParentOrgCode());
        if (StringUtils.isNotNull(info) && info.getOrgCode().longValue() != orgCode.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验机构是否有数据权限
     * 
     * @param orgCode 机构id
     */
    @Override
    public void checkOrgDataScope(Long orgCode)
    {
        if (!UsrUsers.isAdmin(SecurityUtils.getUsername()) && StringUtils.isNotNull(orgCode))
        {
            UsrOrg org = new UsrOrg();
            org.setOrgCode(orgCode);
            List<UsrOrg> orgs = SpringUtils.getAopProxy(this).selectOrgList(org);
            if (StringUtils.isEmpty(orgs))
            {
                throw new ServiceException("没有权限访问机构数据！");
            }
        }
    }

    /**
     * 新增保存机构信息
     * 
     * @param org 机构信息
     * @return 结果
     */
    @Override
    public int insertOrg(UsrOrg org)
    {
        UsrOrg info = orgMapper.selectOrgById(org.getParentOrgCode());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.ORG_NORMAL.equals(info.getOrgStatus()))
        {
            throw new ServiceException("机构停用，不允许新增");
        }
        org.setAncestors(info.getAncestors() + "," + org.getParentOrgCode());
        return orgMapper.insertOrg(org);
    }

    /**
     * 修改保存机构信息
     * 
     * @param org 机构信息
     * @return 结果
     */
    @Override
    public int updateOrg(UsrOrg org)
    {
        UsrOrg newParentOrg = orgMapper.selectOrgById(org.getParentOrgCode());
        UsrOrg oldOrg = orgMapper.selectOrgById(org.getOrgCode());
        if (StringUtils.isNotNull(newParentOrg) && StringUtils.isNotNull(oldOrg))
        {
            String newAncestors = newParentOrg.getAncestors() + "," + newParentOrg.getOrgCode();
            String oldAncestors = oldOrg.getAncestors();
            org.setAncestors(newAncestors);
            updateOrgChildren(org.getOrgCode(), newAncestors, oldAncestors);
        }
        int result = orgMapper.updateOrg(org);
        if (UserConstants.ORG_NORMAL.equals(org.getOrgStatus()) && StringUtils.isNotEmpty(org.getAncestors())
                && !StringUtils.equals("0", org.getAncestors()))
        {
            // 如果该机构是启用状态，则启用该机构的所有上级机构
            updateParentOrgStatusNormal(org);
        }
        return result;
    }

    /**
     * 修改该机构的父级机构状态
     * 
     * @param org 当前机构
     */
    private void updateParentOrgStatusNormal(UsrOrg org)
    {
        String ancestors = org.getAncestors();
        Long[] orgCodes = Convert.toLongArray(ancestors);
        orgMapper.updateOrgStatusNormal(orgCodes);
    }

    /**
     * 修改子元素关系
     * 
     * @param orgCode 被修改的机构ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateOrgChildren(Long orgCode, String newAncestors, String oldAncestors)
    {
        List<UsrOrg> children = orgMapper.selectChildrenOrgById(orgCode);
        for (UsrOrg child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            orgMapper.updateOrgChildren(children);
        }
    }

    /**
     * 删除机构管理信息
     * 
     * @param orgCode 机构ID
     * @return 结果
     */
    @Override
    public int deleteOrgById(Long orgCode)
    {
        return orgMapper.deleteOrgById(orgCode);
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<UsrOrg> list, UsrOrg t)
    {
        // 得到子节点列表
        List<UsrOrg> childList = getChildList(list, t);
        t.setChildren(childList);
        for (UsrOrg tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<UsrOrg> getChildList(List<UsrOrg> list, UsrOrg t)
    {
        List<UsrOrg> tlist = new ArrayList<UsrOrg>();
        Iterator<UsrOrg> it = list.iterator();
        while (it.hasNext())
        {
            UsrOrg n = (UsrOrg) it.next();
            if (StringUtils.isNotNull(n.getParentOrgCode()) && n.getParentOrgCode().longValue() == t.getOrgCode().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<UsrOrg> list, UsrOrg t)
    {
        return getChildList(list, t).size() > 0;
    }
}
