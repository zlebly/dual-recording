package com.georsoft.business.service.Impl;

import com.georsoft.business.entity.PO.CustomerReportPO;
import com.georsoft.business.entity.PO.OrgReportPO;
import com.georsoft.business.entity.VO.CustomerReportVO;
import com.georsoft.business.entity.VO.OrgReportVO;
import com.georsoft.business.mapper.OrgMapper;
import com.georsoft.business.service.ReportService;
import com.georsoft.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    OrgMapper orgMapper;

    /**
     * 获取用户双录报表
     * @param orgCode 机构号
     * @param userId 用户号
     * @return AjaxResult
     */
    @Override
    public AjaxResult getCustomerReport(String orgCode, String userId) {
        List<CustomerReportPO> list = orgMapper.qryCustomerReport(orgCode, userId);
        List<CustomerReportVO> voList = new ArrayList<>();
        BeanUtils.copyProperties(list, voList);
        return AjaxResult.success(voList);
    }

    /**
     * 获取机构双录报表
     * @param orgCode 机构号
     * @return AjaxResult
     */
    @Override
    public AjaxResult getOrgReport(String orgCode) {
        List<OrgReportPO> list = orgMapper.qryOrgReport(orgCode);
        List<OrgReportVO> voList = new ArrayList<>();
        BeanUtils.copyProperties(list, voList);
        return AjaxResult.success(voList);
    }
}
