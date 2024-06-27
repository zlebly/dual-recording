package com.georsoft.business.mapper;

import com.georsoft.business.entity.DTO.QryCustomerInfoDTO;
import com.georsoft.business.entity.PO.CustomerInfoPO;
import com.georsoft.business.entity.PO.CustomerPO;
import com.georsoft.business.entity.PO.ProductInfoPO;

import java.util.List;

public interface CustomerInfoMapper {
    /**
     * 根据客户姓名等数据查询客户信息
     * @param data data
     *
     * @author douwenjie
     * @return CustomerInfoPO
     */
    List<CustomerInfoPO> qryCustomerInfo(QryCustomerInfoDTO data);

    /**
     * 更新用户信息
     * @param data 用户信息
     */
    void updateCustomerInfo(CustomerPO data);

    /**
     * 添加用户信息
     * @param data 用户信息
     */
    void addCustomerInfo(CustomerPO data);

    /**
     * 删除用户信息
     * @param id 用户id
     */
    void deleteCustomerInfo(String id);
}
