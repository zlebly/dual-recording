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
    private Long roleCode;
    
    /** 机构码 */
    private Long orgCode;

    public Long getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(Long roleCode)
    {
        this.roleCode = roleCode;
    }

    public Long getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(Long orgCode)
    {
        this.orgCode = orgCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleCode())
            .append("orgId", getOrgCode())
            .toString();
    }
}
