package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.DTO.QryCustomerInfoDTO;
import com.georsoft.business.entity.PO.CustomerPO;
import com.georsoft.business.entity.PO.CustomerReportPO;
import com.georsoft.business.entity.VO.CustomerReportVO;

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

    public static CustomerReportVO convertToCustomerReportVO (CustomerReportPO customer) {
        CustomerReportVO customerVO = new CustomerReportVO();
        customerVO.setImageCount(customer.getImageCount());
        customerVO.setOrgCode(customer.getOrgCode());
        customerVO.setUserId(customer.getUserId());
        customerVO.setUserName(customer.getUserName());
        return customerVO;
    }
}
