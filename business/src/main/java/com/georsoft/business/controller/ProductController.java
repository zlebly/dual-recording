package com.georsoft.business.controller;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.service.ProductInfoService;
import com.georsoft.common.annotation.Anonymous;
import com.georsoft.common.core.controller.BaseController;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductInfoService productInfoService;

    @PreAuthorize("@ss.hasPermi('productInfo:list')")
    @PostMapping("/list")
    public TableDataInfo getProductInfoList(@RequestBody QryProductInfoDTO data) {
        startPage();
        List<ProductInfoPO> list = productInfoService.qryProductInfoList(data);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('productInfo:add')")
    @PostMapping("/add")
    public AjaxResult addProductInfo(@RequestBody QryProductInfoDTO data) {
        productInfoService.addProductInfo(data);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('productInfo:update')")
    @PostMapping("/update")
    public AjaxResult updateProductInfoList(@RequestBody QryProductInfoDTO data) {
        productInfoService.updateProductInfo(data);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('productInfo:delete')")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteProductInfoList(@PathVariable("id") String id) {
        productInfoService.deleteProductInfo(id);
        return success();
    }
}