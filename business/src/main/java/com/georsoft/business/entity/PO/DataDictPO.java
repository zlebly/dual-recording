package com.georsoft.business.entity.PO;

import lombok.Data;

/**
 * 字典数据表
 * @author douwenjie
 * sys_data_dict_type
 */
@Data
public class DataDictPO {
    /**
     * 字典主键
     */
    private String id;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 显示顺序
     */
    private String viewIndex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String enableDate;

    /**
     * 失效时间
     */
    private String expireDate;

    /**
     * 停用标志
     */
    private String stopFlag;

    /**
     * 关联字典表
     */
    private String relDictCode;

}
