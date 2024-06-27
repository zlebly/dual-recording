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

    /**
     * 添加产品信息
     * @param data 产品信息
     */
    void addProductInfo(QryCustomerInfoDTO data);

    /**
     * 修改产品信息
     * @param data 产品信息
     */
    void updateProductInfo(QryCustomerInfoDTO data);

    /**
     * 删除产品信息
     * @param id 产品信息id
     */
    void deleteProductInfo(String id);
}
