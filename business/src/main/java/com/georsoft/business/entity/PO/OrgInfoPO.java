package com.georsoft.business.entity.PO;

import lombok.Data;

@Data
public class OrgInfoPO {
    private String orgCode;

    private String parentOrgCode;

    private String ancestors;

    private String abbName;

    private String viewIndex;

    private String orgPhone;

    private String orgAddress;

    private String orgStatus;

    private String scanFlag;

    private String create_by;

    private String create_time;

    private String update_by;

    private String update_time;

}
