package com.georsoft.business.entity.PO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表 sys_user
 */

@Data
public class CustomerPO {
    private String id; // 客户ID
    private String cusName; // 客户姓名
    private String idCard; // 身份证号码
    private String createDate; // 创建日期
    private String productId; // 产品ID
    private String mark; // 备注
    private String idType; // 证件类型
    private String cusAccNo; // 客户账号
    private String userId; // 用户ID
    private String orgCode; // 组织机构代码
    private String depNo; // 部门编号
    private String marketNo; // 市场编号
    private String imageNo; // 图片编号
    private String association; // 关联信息
    private Date assoctationTime; // 关联时间
}
