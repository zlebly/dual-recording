package com.georsoft.business.mapper;

import com.georsoft.business.entity.PO.DataDictPO;

import java.util.List;

public interface DictMapper {

    /**
     * 根据字典名查询字典数据集合
     */
    List<DataDictPO> selectDictDataList(String dictDataTypeName);
}
