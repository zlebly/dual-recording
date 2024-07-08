package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.PO.OrgReportPO;
import com.georsoft.business.entity.VO.OrgOptionsVO;
import com.georsoft.business.entity.VO.OrgReportVO;
import com.georsoft.common.core.domain.entity.UsrOrg;

//@Mapper(componentModel = "spring")
public class OrgConvertBasic {
//    OrgConvertBasic INSTANCE = Mappers.getMapper(OrgConvertBasic.class);

    public static OrgOptionsVO toOrgOptionsVO(UsrOrg data){
        if ( data == null ) {
            return null;
        }

        OrgOptionsVO orgOptionsVO = new OrgOptionsVO();
        orgOptionsVO.setOrgCode(data.getOrgCode());
        orgOptionsVO.setOrgName(data.getOrgName());
        return orgOptionsVO;
    }

    public static OrgReportVO toOrgReportVO(OrgReportPO data){
        if ( data == null ) {
            return null;
        }
        OrgReportVO orgReportVO = new OrgReportVO();
        orgReportVO.setOrgCode(data.getOrgCode());
        orgReportVO.setImageCount(data.getImageCount());
        return orgReportVO;
    }
}
