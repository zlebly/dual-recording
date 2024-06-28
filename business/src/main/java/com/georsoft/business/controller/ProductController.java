package com.georsoft.business.controller;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.entity.VO.ProductInfoVO;
import com.georsoft.business.service.ProductInfoService;
import com.georsoft.common.annotation.Anonymous;
import com.georsoft.common.core.controller.BaseController;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.core.page.TableDataInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductInfoService productInfoService;

    @ApiOperation("获取产品信息展示列表")
    @PreAuthorize("@ss.hasPermi('productInfo:list')")
    @GetMapping("/list")
    public TableDataInfo getProductInfoList(QryProductInfoDTO data) {
        startPage();
        List<ProductInfoPO> list = productInfoService.qryProductInfoList(data);
        return getDataTable(list);
    }

    @ApiOperation("新增产品信息")
    @PreAuthorize("@ss.hasPermi('productInfo:add')")
    @PostMapping("/add")
    public AjaxResult addProductInfo(@RequestBody QryProductInfoDTO data) {
        productInfoService.addProductInfo(data);
        return success();
    }

    @ApiOperation("更新产品信息")
    @PreAuthorize("@ss.hasPermi('productInfo:update')")
    @PostMapping("/update")
    public AjaxResult updateProductInfoList(@RequestBody QryProductInfoDTO data) {
        productInfoService.updateProductInfo(data);
        return success();
    }

    @ApiOperation("删除产品信息")
    @PreAuthorize("@ss.hasPermi('productInfo:delete')")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteProductInfoList(@PathVariable("id") String id) {
        productInfoService.deleteProductInfo(id);
        return success();
    }

    @ApiOperation("获取产品信息选择列表")
    @GetMapping("/getProductInfo")
    public AjaxResult getProductInfo() {
        List<ProductInfoPO> list = productInfoService.qryProductInfoList(new QryProductInfoDTO());
        List<ProductInfoVO> voList = new ArrayList<>();
        for (ProductInfoPO productInfoPO : list) {
            ProductInfoVO vo = new ProductInfoVO();
            BeanUtils.copyProperties(productInfoPO, vo);
            vo.setProductName("[" + productInfoPO.getProductId() + "]" + productInfoPO.getProductName());
            voList.add(vo);
        }
        return AjaxResult.success(voList);
    }
}