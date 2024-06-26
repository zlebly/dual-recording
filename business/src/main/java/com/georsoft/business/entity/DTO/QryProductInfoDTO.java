package com.georsoft.business.entity.DTO;

import lombok.Data;

@Data
public class QryProductInfoDTO {

    /**
     * 产品编号
     */
    private String id;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 风险提示
     */
    private String riskTips;

    /**
     * 创建时间起
     */
    private String createTimeStart;

    /**
     * 创建时间止
     */
    private String createTimeEnd;
}
