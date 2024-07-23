package com.georsoft.common.core.domain.entity;

import java.util.Arrays;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.georsoft.common.annotation.Excel;
import com.georsoft.common.annotation.Excel.ColumnType;
import com.georsoft.common.core.domain.BaseEntity;

/**
 * 角色表 usr_role
 * 
 * @author douwenjie
 */
public class UsrRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    @Excel(name = "角色序号", cellType = ColumnType.NUMERIC)
    private String roleCode;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 角色排序 */
    @Excel(name = "角色排序")
    private Integer viewIndex;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    /** 机构分类 */
    @Excel(name = "机构分类")
    private String orgClass;

    /** 登录标志 */
    @Excel(name = "登录标志")
    private Boolean signInFlag;

    // TODO 数据范围
//    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本机构数据权限；4：本机构及以下数据权限；5：仅本人数据权限） */
//    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本机构数据权限,4=本机构及以下数据权限,5=仅本人数据权限")
//    private String dataScope;

    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

    /** 菜单组 */
    private String[] functionCodes;

    /** 机构组（数据权限） */
    private String[] orgCodes;

    /** 角色菜单权限 */
    private Set<String> permissions;

    public UsrRole()
    {

    }

    public UsrRole(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public String getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.roleCode);
    }

    public static boolean isAdmin(String roleCode)
    {
        return roleCode != null && "1" == roleCode;
    }

    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getViewIndex()
    {
        return viewIndex;
    }

    public void setViewIndex(Integer viewIndex)
    {
        this.viewIndex = viewIndex;
    }

//    public String getDataScope()
//    {
//        return dataScope;
//    }
//
//    public void setDataScope(String dataScope)
//    {
//        this.dataScope = dataScope;
//    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    public String[] getFunctionCodes()
    {
        return functionCodes;
    }

    public void setFunctionCodes(String[] functionCodes)
    {
        this.functionCodes = functionCodes;
    }

    public String[] getOrgCodes()
    {
        return orgCodes;
    }

    public void setOrgCodes(String[] orgCodes)
    {
        this.orgCodes = orgCodes;
    }

    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgClass() {
        return orgClass;
    }

    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass;
    }

    public Boolean getSignInFlag() {
        return signInFlag;
    }

    public void setSignInFlag(Boolean signInFlag) {
        this.signInFlag = signInFlag;
    }

    @Override
    public String toString() {
        return "UsrRole{" +
                "roleCode=" + roleCode +
                ", roleName='" + roleName + '\'' +
                ", viewIndex=" + viewIndex +
                ", remark='" + remark + '\'' +
                ", orgClass='" + orgClass + '\'' +
                ", signInFlag=" + signInFlag +
                ", flag=" + flag +
                ", functionCodes=" + Arrays.toString(functionCodes) +
                ", orgCodes=" + Arrays.toString(orgCodes) +
                ", permissions=" + permissions +
                '}';
    }
}
