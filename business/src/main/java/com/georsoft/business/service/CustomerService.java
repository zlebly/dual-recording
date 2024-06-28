package com.georsoft.business.service;

import com.georsoft.business.entity.DTO.QryCustomerInfoDTO;
import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.CustomerInfoPO;
import com.georsoft.common.core.domain.AjaxResult;

import java.util.List;

public interface CustomerService {
    AjaxResult getDictDataList();

    AjaxResult getCurrentAndSubOrg();

    AjaxResult getUserInfoByOrg();

    List<CustomerInfoPO> getCustomerInfo(QryCustomerInfoDTO qryCustomerInfoDTO);

    void addProductInfo(QryCustomerInfoDTO data);

    void updateProductInfo(QryCustomerInfoDTO data);

    void deleteProductInfo(String id);
}
