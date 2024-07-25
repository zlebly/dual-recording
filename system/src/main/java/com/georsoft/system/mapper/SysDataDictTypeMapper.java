package com.georsoft.system.mapper;

import java.util.List;
import com.georsoft.common.core.domain.entity.SysDataDictType;

/**
 * 字典表 数据层
 * 
 * @author douwenjie
 */
public interface SysDataDictTypeMapper
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
     * 根据字典类型ID查询信息
     * 
     * @param id 字典类型ID
     * @return 字典类型
     */
    public SysDataDictType selectDictTypeById(Long id);

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    public SysDataDictType selectDictTypeByType(String dictType);

    /**
     * 通过字典ID删除字典信息
     * 
     * @param dictId 字典ID
     * @return 结果
     */
    public int deleteDictTypeById(Long dictId);

    /**
     * 批量删除字典类型信息
     * 
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 新增字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(SysDataDictType dictType);

    /**
     * 修改字典类型信息
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
    public SysDataDictType checkDictTypeUnique(String dictType);
}
