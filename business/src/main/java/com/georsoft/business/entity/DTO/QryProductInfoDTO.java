package com.georsoft.business.entity.DTO;

import java.time.LocalDateTime;

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
    private LocalDateTime createTimeStart;

    /**
     * 创建时间止
     */
    private LocalDateTime createTimeEnd;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public LocalDateTime getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(LocalDateTime createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRiskTips() {
        return riskTips;
    }

    public void setRiskTips(String riskTips) {
        this.riskTips = riskTips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QryProductInfoDTO{" +
                " productId='" + productId + '\'' +
                ", createTimeStart=" + createTimeStart +
                ", createTimeEnd=" + createTimeEnd +
                '}';
    }
}
