package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.entity.VO.ProductInfoVO;

public class ProductConvert {

    public static ProductInfoPO toProductInfoPO(QryProductInfoDTO data) {
        if ( data == null ) {
            return null;
        }
        ProductInfoPO po = new ProductInfoPO();
        po.setId(data.getId());
        po.setProductId(data.getProductId());
        po.setProductName(data.getProductName());
        po.setProductType(data.getProductType());
        po.setRiskTips(data.getRiskTips());
        return po;
    }

    public static ProductInfoPO VOToProductInfoPO(ProductInfoVO data) {
        if ( data == null ) {
            return null;
        }
        ProductInfoPO po = new ProductInfoPO();
        po.setProductId(data.getProductId());
        po.setProductName(data.getProductName());
        po.setProductType(data.getProductType());
        po.setRiskTips(data.getRiskTips());
        return po;
    }

}
