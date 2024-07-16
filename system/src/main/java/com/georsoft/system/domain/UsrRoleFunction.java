package com.georsoft.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和菜单关联 usr_role_function
 * 
 * @author douwenjie
 */
public class UsrRoleFunction
{
    /** 角色ID */
    private Long roleCode;
    
    /** 菜单ID */
    private Long functionCode;

    public Long getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(Long roleCode)
    {
        this.roleCode = roleCode;
    }

    public Long getFunctionCode()
    {
        return functionCode;
    }

    public void setFunctionCode(Long functionCode)
    {
        this.functionCode = functionCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleCode())
            .append("menuId", getFunctionCode())
            .toString();
    }
}
