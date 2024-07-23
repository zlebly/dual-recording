package com.georsoft.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户和角色关联 usr_user_role
 * 
 * @author douwenjie
 */
public class UsrUserRole
{
    /** 用户登陆名 */
    private String loginName;
    
    /** 角色ID */
    private String roleCode;

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getRoleCode()
    {
        return roleCode;
    }

    public void setRoleCode(String roleCode)
    {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getLoginName())
            .append("roleCode", getRoleCode())
            .toString();
    }
}
