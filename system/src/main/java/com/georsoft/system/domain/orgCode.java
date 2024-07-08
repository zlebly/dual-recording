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
    private Long roleId;
    
    /** 机构码 */
    private Long orgCode;

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
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
            .append("roleId", getRoleId())
            .append("orgId", getOrgCode())
            .toString();
    }
}
