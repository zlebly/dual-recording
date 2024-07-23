package com.georsoft.common.core.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.georsoft.common.core.domain.BaseEntity;

/**
 * 机构表 usr_org
 * 
 * @author douwenjie
 */
public class UsrOrg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 机构码 */
    private String orgCode;

    /** 父机构码 */
    private String parentOrgCode;

    private String ancestors;

    /** 机构名称 */
    private String orgName;

    /** 别名 */
    private String abbName;

    /** 机构类型 */
    private String orgType;

    /** 机构类别 */
    private String orgClass;

    /** 是否扫描 */
    private Boolean scanFlag;

    /** 是否叶子节点 */
    private Boolean isLeaf;

    /** 全机构码 */
    private String fullOrgCode;

    /** 银行账号 */
    private String bankCodeAccount;

    /** 模型码 */
    private String modelCode;

    /** 显示顺序 */
    private Integer viewIndex;

    /** 联系电话 */
    private String orgPhone;

    /** 机构地址 */
    private String orgAddress;

    /** 机构状态:0正常,1停用 */
    private String orgStatus;

    /** 父部门名称 */
    private String parentName;
    
    /** 子部门 */
    private List<UsrOrg> children = new ArrayList<UsrOrg>();

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    public String getParentOrgCode()
    {
        return parentOrgCode;
    }

    public void setParentOrgCode(String parentOrgCode)
    {
        this.parentOrgCode = parentOrgCode;
    }

    public String getAncestors()
    {
        return ancestors;
    }

    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    public String getOrgName()
    {
        return orgName;
    }

    public void setOrgName(String orgName)
    {
        this.orgName = orgName;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getViewIndex()
    {
        return viewIndex;
    }

    public void setViewIndex(Integer viewIndex)
    {
        this.viewIndex = viewIndex;
    }

    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    public String getOrgPhone()
    {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone)
    {
        this.orgPhone = orgPhone;
    }

    @Size(min = 0, max = 60, message = "地址长度不能超过60个字符")
    public String getOrgAddress()
    {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress)
    {
        this.orgAddress = orgAddress;
    }

    public String getOrgStatus()
    {
        return orgStatus;
    }

    public void setOrgStatus(String orgStatus)
    {
        this.orgStatus = orgStatus;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public List<UsrOrg> getChildren()
    {
        return children;
    }

    public void setChildren(List<UsrOrg> children)
    {
        this.children = children;
    }

    public String getAbbName() {
        return abbName;
    }

    public void setAbbName(String abbName) {
        this.abbName = abbName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getOrgClass() {
        return orgClass;
    }

    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass;
    }

    public Boolean getScanFlag() {
        return scanFlag;
    }

    public void setScanFlag(Boolean scanFlag) {
        this.scanFlag = scanFlag;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public String getFullOrgCode() {
        return fullOrgCode;
    }

    public void setFullOrgCode(String fullOrgCode) {
        this.fullOrgCode = fullOrgCode;
    }

    public String getBankCodeAccount() {
        return bankCodeAccount;
    }

    public void setBankCodeAccount(String bankCodeAccount) {
        this.bankCodeAccount = bankCodeAccount;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    @Override
    public String toString() {
        return "UsrOrg{" +
                "orgCode=" + orgCode +
                ", parentOrgCode=" + parentOrgCode +
                ", ancestors='" + ancestors + '\'' +
                ", orgName='" + orgName + '\'' +
                ", abbName='" + abbName + '\'' +
                ", orgType='" + orgType + '\'' +
                ", orgClass='" + orgClass + '\'' +
                ", scanFlag=" + scanFlag +
                ", isLeaf=" + isLeaf +
                ", fullOrgCode='" + fullOrgCode + '\'' +
                ", bankCodeAccount='" + bankCodeAccount + '\'' +
                ", modelCode='" + modelCode + '\'' +
                ", viewIndex=" + viewIndex +
                ", orgPhone='" + orgPhone + '\'' +
                ", orgAddress='" + orgAddress + '\'' +
                ", orgStatus='" + orgStatus + '\'' +
                ", parentName='" + parentName + '\'' +
                ", children=" + children +
                '}';
    }
}
