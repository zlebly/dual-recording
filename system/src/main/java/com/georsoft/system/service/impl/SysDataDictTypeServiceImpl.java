package com.georsoft.system.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.georsoft.common.constant.UserConstants;
import com.georsoft.common.core.domain.entity.SysDataDict;
import com.georsoft.common.core.domain.entity.SysDataDictType;
import com.georsoft.common.exception.ServiceException;
import com.georsoft.common.utils.DictUtils;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.system.mapper.SysDataDictMapper;
import com.georsoft.system.mapper.SysDataDictTypeMapper;
import com.georsoft.system.service.ISysDataDictTypeService;

/**
 * 字典 业务层处理
 * 
 * @author douwenjie
 */
@Service
public class SysDataDictTypeServiceImpl implements ISysDataDictTypeService
{
    @Autowired
    private SysDataDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDataDictMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init()
    {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDataDictType> selectDictTypeList(SysDataDictType dictType)
    {
        return dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDataDictType> selectDictTypeAll()
    {
        return dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDataDict> selectDictDataByType(String dictType)
    {
        List<SysDataDict> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }

    /**
     * 根据字典类型ID查询信息
     * 
     * @param id 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDataDictType selectDictTypeById(Long id)
    {
        return dictTypeMapper.selectDictTypeById(id);
    }

    /**
     * 根据字典类型查询信息
     * 
     * @param dictCode 字典类型
     * @return 字典类型
     */
    @Override
    public SysDataDictType selectDictTypeByType(String dictCode)
    {
        return dictTypeMapper.selectDictTypeByType(dictCode);
    }

    /**
     * 批量删除字典类型信息
     * 
     * @param ids 需要删除的字典ID
     */
    @Override
    public void deleteDictTypeByIds(Long[] ids)
    {
        for (Long dictId : ids)
        {
            SysDataDictType dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getDictCode()) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            dictTypeMapper.deleteDictTypeById(dictId);
            DictUtils.removeDictCache(dictType.getDictCode());
        }
    }

    /**
     * 加载字典缓存数据
     */
    @Override
    public void loadingDictCache()
    {
        SysDataDict dictData = new SysDataDict();
        dictData.setStopFlag("0");
        Map<String, List<SysDataDict>> dictDataMap = dictDataMapper.selectDictDataList(dictData).stream().collect(Collectors.groupingBy(SysDataDict::getDictType));
        for (Map.Entry<String, List<SysDataDict>> entry : dictDataMap.entrySet())
        {
            DictUtils.setDictCache(entry.getKey(), entry.getValue().stream().sorted(Comparator.comparing(SysDataDict::getViewIndex)).collect(Collectors.toList()));
        }
    }

    /**
     * 清空字典缓存数据
     */
    @Override
    public void clearDictCache()
    {
        DictUtils.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    @Override
    public void resetDictCache()
    {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     * 
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDataDictType dict)
    {
        int row = dictTypeMapper.insertDictType(dict);
        if (row > 0)
        {
            DictUtils.setDictCache(dict.getDictCode(), null);
        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     * 
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDataDictType dict)
    {
        SysDataDictType oldDict = dictTypeMapper.selectDictTypeById(dict.getId());
        dictDataMapper.updateDictDataType(oldDict.getDictCode(), dict.getDictCode());
        int row = dictTypeMapper.updateDictType(dict);
        if (row > 0)
        {
            List<SysDataDict> dictDatas = dictDataMapper.selectDictDataByType(dict.getDictCode());
            DictUtils.setDictCache(dict.getDictCode(), dictDatas);
        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public boolean checkDictTypeUnique(SysDataDictType dict)
    {
        Long dictId = StringUtils.isNull(dict.getId()) ? -1L : dict.getId();
        SysDataDictType dictType = dictTypeMapper.checkDictTypeUnique(dict.getDictCode());
        if (StringUtils.isNotNull(dictType) && dictType.getId().longValue() != dictId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
