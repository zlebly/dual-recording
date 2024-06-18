package com.georsoft.business.entity.PO;

public class ProductInfoPO {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRiskTips() {
        return riskTips;
    }

    public void setRiskTips(String riskTips) {
        this.riskTips = riskTips;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public String toString() {
        return "ProductInfoPO{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", des='" + des + '\'' +
                ", createDate='" + createDate + '\'' +
                ", productId='" + productId + '\'' +
                ", riskTips='" + riskTips + '\'' +
                ", createUserId='" + createUserId + '\'' +
                '}';
    }
}
