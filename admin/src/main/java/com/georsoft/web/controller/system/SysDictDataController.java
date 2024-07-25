package com.georsoft.web.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.georsoft.system.constant.DictionaryType;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.georsoft.common.annotation.Log;
import com.georsoft.common.core.controller.BaseController;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.core.domain.entity.SysDataDict;
import com.georsoft.common.core.page.TableDataInfo;
import com.georsoft.common.enums.BusinessType;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.common.utils.poi.ExcelUtil;
import com.georsoft.system.service.ISysDataDictService;
import com.georsoft.system.service.ISysDataDictTypeService;

/**
 * 数据字典信息
 * 
 * @author douwenjie
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDataDictService dictDataService;

    @Autowired
    private ISysDataDictTypeService dictTypeService;

    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDataDict dictData)
    {
        startPage();
        List<SysDataDict> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:dict:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDataDict dictData)
    {
        List<SysDataDict> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDataDict> util = new ExcelUtil<SysDataDict>(SysDataDict.class);
        util.exportExcel(response, list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable Long dictCode)
    {
        return success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        DictionaryType instance = DictionaryType.getInstance();
        Map<String, Integer> types = instance.getDictionaryTypes();
        String type = types.get(dictType).toString();
        List<SysDataDict> data = dictTypeService.selectDictDataByType(Strings.isEmpty(type) ? dictType : type);
        if (StringUtils.isNull(data))
        {
            data = new ArrayList<SysDataDict>();
        }
        return success(data);
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDataDict dict)
    {
        dict.setCreateBy(getUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDataDict dict)
    {
        dict.setUpdateBy(getUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        dictDataService.deleteDictDataByIds(dictCodes);
        return success();
    }
}
