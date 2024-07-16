package com.georsoft.system.service;

import java.util.List;
import com.georsoft.common.core.domain.entity.SysDataDict;
import com.georsoft.common.core.domain.entity.SysDataDictType;

/**
 * 字典 业务层
 * 
 * @author douwenjie
 */
public interface ISysDataDictTypeService
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<SysDataDictType> selectDictTypeList(SysDataDictType dictType);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<SysDataDictType> selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<SysDataDict> selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     * 
     * @param id 字典类型ID
     * @return 字典类型
     */
    public SysDataDictType selectDictTypeById(Long id);

    /**
     * 根据字典类型查询信息
     * 
     * @param dictCode 字典类型
     * @return 字典类型
     */
    public SysDataDictType selectDictTypeByType(String dictCode);

    /**
     * 批量删除字典信息
     * 
     * @param ids 需要删除的字典ID
     */
    public void deleteDictTypeByIds(Long[] ids);

    /**
     * 加载字典缓存数据
     */
    public void loadingDictCache();

    /**
     * 清空字典缓存数据
     */
    public void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    public void resetDictCache();

    /**
     * 新增保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(SysDataDictType dictType);

    /**
     * 修改保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int updateDictType(SysDataDictType dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public boolean checkDictTypeUnique(SysDataDictType dictType);
}
