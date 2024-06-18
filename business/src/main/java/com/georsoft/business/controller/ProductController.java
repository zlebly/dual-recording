package com.georsoft.business.controller;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.service.ProductInfoService;
import com.georsoft.common.core.controller.BaseController;
import com.georsoft.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductInfoService productInfoService;

    @PreAuthorize("@ss.hasPermi('productInfo:list')")
    @GetMapping("/list")
    public TableDataInfo getProductInfoList(QryProductInfoDTO data) {
        startPage();
        List<ProductInfoPO> list = productInfoService.selectProductInfoList(data);
        return getDataTable(list);
    }
}