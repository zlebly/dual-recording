package com.georsoft.business.entity.PO;

import lombok.Data;

/**
 * 用户信息列表展示信息
 */
@Data
public class CustomerInfoPO {
    private String id;

    private String cusName; // 客户姓名

    private String idType; // 身份证件类型

    private String idCard; // 身份证号码

    private String cusAccNo; // 客户账号

    private String productId; // 产品ID

    private String userName; // 用户ID

    private String createDate; // 创建日

    private String imageNo; // 图片编号

    private String orgCode; // 组织机构代码
}
