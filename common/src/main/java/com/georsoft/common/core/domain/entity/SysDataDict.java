package com.georsoft.common.core.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.georsoft.common.annotation.Excel;
import com.georsoft.common.annotation.Excel.ColumnType;
import com.georsoft.common.constant.UserConstants;
import com.georsoft.common.core.domain.BaseEntity;

/**
 * 字典数据表 sys_data_dict
 * 
 * @author douwenjie
 */
public class SysDataDict extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典编号 */
    @Excel(name = "字典编号", cellType = ColumnType.NUMERIC)
    private Long id;

    /** 字典排序 */
    @Excel(name = "字典排序", cellType = ColumnType.NUMERIC)
    private Long viewIndex;

    /** 字典名称 */
    @Excel(name = "字典名称")
    private String dictName;

    /** 字典编码 */
    @Excel(name = "字典编码")
    private String dictCode;

    /** 字典类型 */
    @Excel(name = "字典类型")
    private String dictType;

    /** 停用标记（0正常 1停用） */
    @Excel(name = "停用标记", readConverterExp = "0=正常,1=停用")
    private String stopFlag;

    @Excel(name = "启用日期")
    private String enableDate;

    @Excel(name = "失效日期")
    private String expireDate;

    private String relDictCode;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getViewIndex()
    {
        return viewIndex;
    }

    public void setViewIndex(Long viewIndex)
    {
        this.viewIndex = viewIndex;
    }

    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
    public String getDictCode()
    {
        return dictCode;
    }

    public void setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
    }

    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getStopFlag()
    {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag)
    {
        this.stopFlag = stopFlag;
    }

    public String getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(String enableDate) {
        this.enableDate = enableDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getRelDictCode() {
        return relDictCode;
    }

    public void setRelDictCode(String relDictCode) {
        this.relDictCode = relDictCode;
    }

    @Override
    public String toString() {
        return "SysDataDict{" +
                "id=" + id +
                ", viewIndex=" + viewIndex +
                ", dictName='" + dictName + '\'' +
                ", dictCode='" + dictCode + '\'' +
                ", dictType='" + dictType + '\'' +
                ", stopFlag='" + stopFlag + '\'' +
                ", enableDate='" + enableDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", relDictCode='" + relDictCode + '\'' +
                '}';
    }
}
