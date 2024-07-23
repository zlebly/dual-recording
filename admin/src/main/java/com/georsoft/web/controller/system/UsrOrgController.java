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
 * 机构信息
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
     * 获取机构列表
     */
    @PreAuthorize("@ss.hasPermi('system:org:list')")
    @GetMapping("/list")
    public AjaxResult list(UsrOrg org)
    {
        List<UsrOrg> orgs = orgService.selectOrgList(org);
        return success(orgs);
    }

    /**
     * 查询机构列表（排除节点）
     */
    @PreAuthorize("@ss.hasPermi('system:org:list')")
    @GetMapping("/list/exclude/{orgCode}")
    public AjaxResult excludeChild(@PathVariable(value = "orgCode", required = false) String orgCode)
    {
        List<UsrOrg> orgs = orgService.selectOrgList(new UsrOrg());
        orgs.removeIf(d -> d.getOrgCode().equals(orgCode) || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), orgCode + ""));
        return success(orgs);
    }

    /**
     * 根据机构编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:org:query')")
    @GetMapping(value = "/{orgCode}")
    public AjaxResult getInfo(@PathVariable String orgCode)
    {
        orgService.checkOrgDataScope(orgCode);
        return success(orgService.selectOrgById(orgCode));
    }

    /**
     * 新增机构
     */
    @PreAuthorize("@ss.hasPermi('system:org:add')")
    @Log(title = "机构管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody UsrOrg org)
    {
        if (!orgService.checkOrgNameUnique(org))
        {
            return error("新增机构'" + org.getOrgName() + "'失败，机构名称已存在");
        }
        org.setCreateBy(getUsername());
        return toAjax(orgService.insertOrg(org));
    }

    /**
     * 修改机构
     */
    @PreAuthorize("@ss.hasPermi('system:org:edit')")
    @Log(title = "机构管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody UsrOrg org)
    {
        String orgCode = org.getOrgCode();
        orgService.checkOrgDataScope(orgCode);
        if (!orgService.checkOrgNameUnique(org))
        {
            return error("修改机构'" + org.getOrgName() + "'失败，机构名称已存在");
        }
        else if (org.getParentOrgCode().equals(orgCode))
        {
            return error("修改机构'" + org.getOrgName() + "'失败，上级机构不能是自己");
        }
        else if (StringUtils.equals(UserConstants.ORG_DISABLE, org.getOrgStatus()) && orgService.selectNormalChildrenOrgById(orgCode) > 0)
        {
            return error("该机构包含未停用的子机构！");
        }
        org.setUpdateBy(getUsername());
        return toAjax(orgService.updateOrg(org));
    }

    /**
     * 删除机构
     */
    @PreAuthorize("@ss.hasPermi('system:org:remove')")
    @Log(title = "机构管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orgCode}")
    public AjaxResult remove(@PathVariable String orgCode)
    {
        if (orgService.hasChildByOrgCode(orgCode))
        {
            return warn("存在下级机构,不允许删除");
        }
        if (orgService.checkOrgExistUser(orgCode))
        {
            return warn("机构存在用户,不允许删除");
        }
        orgService.checkOrgDataScope(orgCode);
        return toAjax(orgService.deleteOrgById(orgCode));
    }
}
