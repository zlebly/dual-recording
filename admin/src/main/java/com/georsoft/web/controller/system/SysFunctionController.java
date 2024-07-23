package com.georsoft.web.controller.system;

import java.util.List;
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
import com.georsoft.common.core.domain.entity.UsrFunctionTree;
import com.georsoft.common.enums.BusinessType;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.system.service.IUsrFunctionTreeService;

/**
 * 菜单信息
 * 
 * @author douwenjie
 */
@RestController
@RequestMapping("/system/function")
public class SysFunctionController extends BaseController
{
    @Autowired
    private IUsrFunctionTreeService usrFunctionTreeService;

    /**
     * 获取菜单列表
     */
    @PreAuthorize("@ss.hasPermi('system:menu:list')")
    @GetMapping("/list")
    public AjaxResult list(UsrFunctionTree functionTree)
    {
        List<UsrFunctionTree> functionTrees = usrFunctionTreeService.selectFunctionList(functionTree, getUserId());
        return success(functionTrees);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:menu:query')")
    @GetMapping(value = "/{functionCode}")
    public AjaxResult getInfo(@PathVariable String functionCode)
    {
        return success(usrFunctionTreeService.selectFunctionById(functionCode));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeSelect")
    public AjaxResult treeSelect(UsrFunctionTree functionTree)
    {
        List<UsrFunctionTree> functionTrees = usrFunctionTreeService.selectFunctionList(functionTree, getUserId());
        return success(usrFunctionTreeService.buildFunctionTreeSelect(functionTrees));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleFunctionTreeSelect/{roleCode}")
    public AjaxResult roleFunctionTreeSelect(@PathVariable("roleCode") String roleCode)
    {
        List<UsrFunctionTree> functionTrees = usrFunctionTreeService.selectFunctionList(getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", usrFunctionTreeService.selectFunctionListByRoleCode(roleCode));
        ajax.put("functionTrees", usrFunctionTreeService.buildFunctionTreeSelect(functionTrees));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody UsrFunctionTree functionTree)
    {
        if (!usrFunctionTreeService.checkFunctionNameUnique(functionTree))
        {
            return error("新增菜单'" + functionTree.getFunctionName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(functionTree.getIsFrame()) && !StringUtils.ishttp(functionTree.getPath()))
        {
            return error("新增菜单'" + functionTree.getFunctionName() + "'失败，地址必须以http(s)://开头");
        }
        return toAjax(usrFunctionTreeService.insertFunction(functionTree));
    }

    /**
     * 修改菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:edit')")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody UsrFunctionTree functionTree)
    {
        if (!usrFunctionTreeService.checkFunctionNameUnique(functionTree))
        {
            return error("修改菜单'" + functionTree.getFunctionName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(functionTree.getIsFrame()) && !StringUtils.ishttp(functionTree.getPath()))
        {
            return error("修改菜单'" + functionTree.getFunctionName() + "'失败，地址必须以http(s)://开头");
        }
        else if (functionTree.getFunctionCode().equals(functionTree.getParentCode()))
        {
            return error("修改菜单'" + functionTree.getFunctionName() + "'失败，上级菜单不能选择自己");
        }
        return toAjax(usrFunctionTreeService.updateFunction(functionTree));
    }

    /**
     * 删除菜单
     */
    @PreAuthorize("@ss.hasPermi('system:menu:remove')")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{functionCode}")
    public AjaxResult remove(@PathVariable("functionCode") String functionCode)
    {
        if (usrFunctionTreeService.hasChildByFunctionCode(functionCode))
        {
            return warn("存在子菜单,不允许删除");
        }
        if (usrFunctionTreeService.checkFunctionExistRole(functionCode))
        {
            return warn("菜单已分配,不允许删除");
        }
        return toAjax(usrFunctionTreeService.deleteFunctionById(functionCode));
    }
}