package com.georsoft.business.entity.DTO;

import java.time.LocalDateTime;

public class QryProductInfoDTO {
    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品编码 对应productId
     */
    private String productCode;

    /**
     * 创建时间起
     */
    private LocalDateTime createTimeStart;

    /**
     * 创建时间止
     */
    private LocalDateTime createTimeEnd;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public LocalDateTime getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(LocalDateTime createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public LocalDateTime getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(LocalDateTime createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    @Override
    public String toString() {
        return "QryProductInfoDTO{" +
                "productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productCode='" + productCode + '\'' +
                ", createTimeStart=" + createTimeStart +
                ", createTimeEnd=" + createTimeEnd +
                '}';
    }
}
