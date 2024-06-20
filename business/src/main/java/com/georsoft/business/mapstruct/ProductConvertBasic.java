package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductConvertBasic {
    ProductConvertBasic INSTANCE = Mappers.getMapper(ProductConvertBasic.class);

    ProductInfoPO toProductInfoPO(QryProductInfoDTO data);

    QryProductInfoDTO toQryProductInfoDTO(ProductInfoPO data);
}
