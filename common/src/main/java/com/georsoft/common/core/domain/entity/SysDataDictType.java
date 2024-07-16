package com.georsoft.common.core.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.georsoft.common.annotation.Excel;
import com.georsoft.common.annotation.Excel.ColumnType;
import com.georsoft.common.core.domain.BaseEntity;

/**
 * 字典类型表 sys_data_dict_type
 * 
 * @author douwenjie
 */
public class SysDataDictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    @Excel(name = "字典主键", cellType = ColumnType.NUMERIC)
    private Long id;

    /** 字典名称 */
    @Excel(name = "字典名称")
    private String dictName;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictType;

    /** 字典编码 */
    @Excel(name = "字典编码")
    private String dictCode;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String stopFlag;

    private String remark;

    /** 排序 */
    @Excel(name = "排序")
    private String viewIndex;

    private String subDictCode;

    private String subDictTable;

    private String subDictCol;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(String viewIndex) {
        this.viewIndex = viewIndex;
    }

    public String getSubDictCode() {
        return subDictCode;
    }

    public void setSubDictCode(String subDictCode) {
        this.subDictCode = subDictCode;
    }

    public String getSubDictTable() {
        return subDictTable;
    }

    public void setSubDictTable(String subDictTable) {
        this.subDictTable = subDictTable;
    }

    public String getSubDictCol() {
        return subDictCol;
    }

    public void setSubDictCol(String subDictCol) {
        this.subDictCol = subDictCol;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    public String getStopFlag()
    {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag)
    {
        this.stopFlag = stopFlag;
    }

    @Override
    public String toString() {
        return "SysDataDictType{" +
                "id=" + id +
                ", dictName='" + dictName + '\'' +
                ", dictType='" + dictType + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", stopFlag='" + stopFlag + '\'' +
                ", remark='" + remark + '\'' +
                ", viewIndex='" + viewIndex + '\'' +
                ", subDictCode='" + subDictCode + '\'' +
                ", subDictTable='" + subDictTable + '\'' +
                ", subDictCol='" + subDictCol + '\'' +
                '}';
    }
}
