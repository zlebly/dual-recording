package com.georsoft.web.controller.system;

import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.georsoft.common.annotation.Log;
import com.georsoft.common.constant.UserConstants;
import com.georsoft.common.core.controller.BaseController;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.core.domain.entity.UsrOrg;
import com.georsoft.common.enums.BusinessType;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.system.service.IUsrOrgService;

/**
 * 部门信息
 *
 * @author douwenjie
 */
@RestController
@RequestMapping("/system/org")
public class UsrOrgController extends BaseController
{
    @Autowired
    private IUsrOrgService orgService;

    /**
     * 获取部门列表
     */
    @PreAuthorize("@ss.hasPermi('system:org:list')")
    @GetMapping("/list")
    public AjaxResult list(UsrOrg org)
    {
        List<UsrOrg> orgs = orgService.selectOrgList(org);
        return success(orgs);
    }

    /**
     * 查询部门列表（排除节点）
     */
    @PreAuthorize("@ss.hasPermi('system:org:list')")
    @GetMapping("/list/exclude/{orgCode}")
    public AjaxResult excludeChild(@PathVariable(value = "orgCode", required = false) Long orgCode)
    {
        List<UsrOrg> orgs = orgService.selectOrgList(new UsrOrg());
        orgs.removeIf(d -> d.getOrgCode().intValue() == orgCode || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), orgCode + ""));
        return success(orgs);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:org:query')")
    @GetMapping(value = "/{orgCode}")
    public AjaxResult getInfo(@PathVariable Long orgCode)
    {
        orgService.checkOrgDataScope(orgCode);
        return success(orgService.selectOrgById(orgCode));
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@ss.hasPermi('system:org:add')")
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody UsrOrg org)
    {
        if (!orgService.checkOrgNameUnique(org))
        {
            return error("新增部门'" + org.getOrgName() + "'失败，部门名称已存在");
        }
        org.setCreateBy(getUsername());
        return toAjax(orgService.insertOrg(org));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('system:org:edit')")
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody UsrOrg org)
    {
        Long orgCode = org.getOrgCode();
        orgService.checkOrgDataScope(orgCode);
        if (!orgService.checkOrgNameUnique(org))
        {
            return error("修改部门'" + org.getOrgName() + "'失败，部门名称已存在");
        }
        else if (org.getParentOrgCode().equals(orgCode))
        {
            return error("修改部门'" + org.getOrgName() + "'失败，上级部门不能是自己");
        }
        else if (StringUtils.equals(UserConstants.ORG_DISABLE, org.getOrgStatus()) && orgService.selectNormalChildrenOrgById(orgCode) > 0)
        {
            return error("该部门包含未停用的子部门！");
        }
        org.setUpdateBy(getUsername());
        return toAjax(orgService.updateOrg(org));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@ss.hasPermi('system:org:remove')")
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orgCode}")
    public AjaxResult remove(@PathVariable Long orgCode)
    {
        if (orgService.hasChildByOrgCode(orgCode))
        {
            return warn("存在下级部门,不允许删除");
        }
        if (orgService.checkOrgExistUser(orgCode))
        {
            return warn("部门存在用户,不允许删除");
        }
        orgService.checkOrgDataScope(orgCode);
        return toAjax(orgService.deleteOrgById(orgCode));
    }
}
