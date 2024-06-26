package com.georsoft.business.service;

import com.georsoft.business.entity.VO.ProductInfoVO;
import com.georsoft.common.core.domain.AjaxResult;

import java.util.List;

public interface ProductFileService {
    AjaxResult addImportExcel(List<ProductInfoVO> list);

    AjaxResult downloadProductTemplateExcel();
}
