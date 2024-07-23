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
    private String roleCode;
    
    /** 菜单ID */
    private String functionCode;

    public String getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public String getFunctionCode()
    {
        return functionCode;
    }

    public void setFunctionCode(String functionCode)
    {
        this.functionCode = functionCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleCode", getRoleCode())
            .append("functionCode", getFunctionCode())
            .toString();
    }
}
