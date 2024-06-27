package com.georsoft.business.entity.PO;

import lombok.Data;

@Data
public class OrgInfoPO {
    private String deptId;

    private String parentId;

    private String ancestors;

    private String deptName;

    private String orderNum;

    private String leader;

    private String phone;

    private String email;

    private String status;

    private String del_flag;

    private String create_by;

    private String create_time;

    private String update_by;

    private String update_time;

}
