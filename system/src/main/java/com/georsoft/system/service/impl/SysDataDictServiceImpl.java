package com.georsoft.system.service.impl;

import java.util.List;
import java.util.Map;

import com.georsoft.system.constant.DictionaryType;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.georsoft.common.core.domain.entity.SysDataDict;
import com.georsoft.common.utils.DictUtils;
import com.georsoft.system.mapper.SysDataDictMapper;
import com.georsoft.system.service.ISysDataDictService;

/**
 * 字典 业务层处理
 * 
 * @author douwenjie
 */
@Service
public class SysDataDictServiceImpl implements ISysDataDictService
{
    @Autowired
    private SysDataDictMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDataDict> selectDictDataList(SysDataDict dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictCode 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictCode)
    {
        return dictDataMapper.selectDictName(dictType, dictCode);
    }

    /**
     * 根据字典数据ID查询信息
     * 
     * @param id 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDataDict selectDictDataById(Long id)
    {
        return dictDataMapper.selectDictDataById(id);
    }

    /**
     * 批量删除字典数据信息
     * 
     * @param ids 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(Long[] ids)
    {
        for (Long dictCode : ids)
        {
            SysDataDict data = selectDictDataById(dictCode);
            dictDataMapper.deleteDictDataById(dictCode);
            List<SysDataDict> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
    }

    /**
     * 新增保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDataDict data)
    {
        int row = dictDataMapper.insertDictData(data);
        if (row > 0)
        {
            List<SysDataDict> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     * 
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDataDict data)
    {
        int row = dictDataMapper.updateDictData(data);
        if (row > 0)
        {
            DictionaryType instance = DictionaryType.getInstance();
            Map<String, Integer> types = instance.getDictionaryTypes();
            String type = types.get(data.getDictType()).toString();
            List<SysDataDict> dictDatas = dictDataMapper.selectDictDataByType(Strings.isEmpty(type) ? data.getDictType() : type);
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }
}
