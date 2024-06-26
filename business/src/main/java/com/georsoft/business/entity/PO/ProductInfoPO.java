package com.georsoft.business.entity.PO;

import com.georsoft.business.entity.ProductInfo;
import lombok.Data;

@Data
public class ProductInfoPO extends ProductInfo {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 描述
     */
    private String des;

    /**
     * 产品创建日期
     */
    private String createDate;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 风险提示
     */
    private String riskTips;

    /**
     * 创建用户ID
     */
    private String createUserId;

    /**
     * 创建用户名称
     */
    private String createUserName;
}
