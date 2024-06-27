package com.georsoft.business.mapper;

import com.georsoft.common.core.domain.entity.SysUser;

import java.util.List;

public interface UserMapper {
    /**
     * 查询当前机构下的用户信息
     * TODO 暂时使用sysUser
     */
    List<SysUser> qryUserInfoByOrg(String currentOrgCode);
}
