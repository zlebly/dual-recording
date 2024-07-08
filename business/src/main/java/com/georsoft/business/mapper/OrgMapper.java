package com.georsoft.business.mapper;

import com.georsoft.business.entity.PO.CustomerReportPO;
import com.georsoft.business.entity.PO.OrgReportPO;
import com.georsoft.common.core.domain.entity.UsrOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrgMapper {
    /**
     * 查询当前机构及子机构信息
     */
    List<UsrOrg> qryCurrentAndSubOrg(@Param("currentOrgCode") String currentOrgCode);

    /**
     * 查询机构或柜员的双录视频数据
     */
    List<CustomerReportPO> qryCustomerReport(@Param("orgCode") String orgCode, @Param("userId") String userId);

    /**
     * 查询机构的双录视频数据
     */
    List<OrgReportPO> qryOrgReport(@Param("orgCode") String orgCode);
}
