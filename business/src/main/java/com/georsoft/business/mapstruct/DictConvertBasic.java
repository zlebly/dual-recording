package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.PO.DataDictPO;
import com.georsoft.business.entity.VO.DataDictOptionsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public class DictConvertBasic {
//    DictConvertBasic INSTANCE = Mappers.getMapper(DictConvertBasic.class);

    public static DataDictOptionsVO toDataDictOptionsVO(DataDictPO data){
        if ( data == null ) {
            return null;
        }

        DataDictOptionsVO dataDictOptionsVO = new DataDictOptionsVO();
        dataDictOptionsVO.setDictCode(data.getDictCode());
        dataDictOptionsVO.setDictName(data.getDictName());

        return dataDictOptionsVO;
    }
}
