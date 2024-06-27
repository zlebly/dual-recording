package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.VO.OrgOptionsVO;
import com.georsoft.common.core.domain.entity.SysDept;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public class OrgConvertBasic {
//    OrgConvertBasic INSTANCE = Mappers.getMapper(OrgConvertBasic.class);

    public static OrgOptionsVO toOrgOptionsVO(SysDept data){
        if ( data == null ) {
            return null;
        }

        OrgOptionsVO orgOptionsVO = new OrgOptionsVO();
        orgOptionsVO.setDeptId(data.getDeptId());
        orgOptionsVO.setDeptName(data.getDeptName());
        return orgOptionsVO;
    }
}
