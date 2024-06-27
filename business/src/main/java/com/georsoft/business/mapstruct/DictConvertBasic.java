package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.PO.DataDictPO;
import com.georsoft.business.entity.VO.DataDictOptionsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DictConvertBasic {
    DictConvertBasic INSTANCE = Mappers.getMapper(DictConvertBasic.class);

    DataDictOptionsVO toDataDictOptionsVO(DataDictPO data);
}
