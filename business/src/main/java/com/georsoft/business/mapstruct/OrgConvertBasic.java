package com.georsoft.business.mapstruct;

import com.georsoft.business.entity.PO.OrgReportPO;
import com.georsoft.business.entity.VO.OrgOptionsVO;
import com.georsoft.business.entity.VO.OrgReportVO;
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
