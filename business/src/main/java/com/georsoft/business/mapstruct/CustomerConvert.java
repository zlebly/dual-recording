package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.DTO.QryCustomerInfoDTO;
import com.georsoft.business.entity.PO.CustomerPO;

public class CustomerConvert {

    // 转换方法
    public static CustomerPO convertToCustomerPO(QryCustomerInfoDTO customer) {
        CustomerPO customerPO = new CustomerPO();
        customerPO.setId(customer.getId());
        customerPO.setCusName(customer.getCusName());
        customerPO.setCusAccNo(customer.getCusAccNo());
        customerPO.setIdType(customer.getIdType());
        customerPO.setIdCard(customer.getIdCard());
        customerPO.setProductId(customer.getProductId());
        return customerPO;
    }
}
