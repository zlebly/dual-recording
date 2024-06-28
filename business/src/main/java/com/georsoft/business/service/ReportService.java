package com.georsoft.business.service;

import com.georsoft.common.core.domain.AjaxResult;

public interface ReportService {
    AjaxResult getCustomerReport(String orgCode, String userId);

    AjaxResult getOrgReport(String orgCode);
}
