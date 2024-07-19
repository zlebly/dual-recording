package com.georsoft.business.mapper;

import com.georsoft.common.core.domain.entity.UsrUsers;

import java.util.List;

public interface UserMapper {
    /**
     * 查询当前机构下的用户信息
     */
    List<UsrUsers> qryUserInfoByOrg(String currentOrgCode);
}
