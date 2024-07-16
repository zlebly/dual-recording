package com.georsoft.system.service;

import java.util.List;
import com.georsoft.common.core.domain.entity.SysDataDict;

/**
 * 字典 业务层
 * 
 * @author douwenjie
 */
public interface ISysDataDictService
{
    /**
     * 根据条件分页查询字典数据
     * 
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    public List<SysDataDict> selectDictDataList(SysDataDict dictData);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     * 
     * @param dictType 字典类型
     * @param dictCode 字典键值
     * @return 字典标签
     */
    public String selectDictLabel(String dictType, String dictCode);

    /**
     * 根据字典数据ID查询信息
     * 
     * @param id 字典数据ID
     * @return 字典数据
     */
    public SysDataDict selectDictDataById(Long id);

    /**
     * 批量删除字典数据信息
     * 
     * @param ids 需要删除的字典数据ID
     */
    public void deleteDictDataByIds(Long[] ids);

    /**
     * 新增保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int insertDictData(SysDataDict dictData);

    /**
     * 修改保存字典数据信息
     * 
     * @param dictData 字典数据信息
     * @return 结果
     */
    public int updateDictData(SysDataDict dictData);
}
