package com.georsoft.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和机构关联 usr_role_org
 * 
 * @author douwenjie
 */
public class orgCode
{
    /** 角色ID */
    private String roleCode;
    
    /** 机构码 */
    private String orgCode;

    public String getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleCode", getRoleCode())
            .append("orgId", getOrgCode())
            .toString();
    }
}
