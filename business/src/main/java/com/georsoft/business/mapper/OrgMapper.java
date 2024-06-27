package com.georsoft.business.mapper;

import com.georsoft.common.core.domain.entity.SysDept;

import java.util.List;

public interface OrgMapper {
    /**
     * 查询当前机构及子机构信息
     * TODO：暂时先使用SysDept
     */
    List<SysDept> qryCurrentAndSubOrg(String currentOrgCode);
}
