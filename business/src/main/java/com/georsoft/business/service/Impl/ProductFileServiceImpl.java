package com.georsoft.business.service.Impl;

import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.entity.VO.ProductInfoVO;
import com.georsoft.business.mapper.ProductInfoMapper;
import com.georsoft.business.mapstruct.ProductConvertBasic;
import com.georsoft.business.service.ProductFileService;
import com.georsoft.business.util.GenerateUtils;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductFileServiceImpl implements ProductFileService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    private static final String TEMPLATE_PATH = "classpath:/static/files/产品代码导入模版.xlsx";

    @Override
    public AjaxResult addImportExcel(List<ProductInfoVO> list) {
        List<ProductInfoPO> productInfoPOList = new ArrayList<>();
        for (ProductInfoVO productInfoVO : list) {
            ProductInfoPO productInfoPO = ProductConvertBasic.INSTANCE.VOToProductInfoPO(productInfoVO);
            productInfoPO.setId(GenerateUtils.IdGenerate());
            productInfoPO.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
            productInfoPO.setCreateUserId(SecurityUtils.getUserId().toString());
            productInfoPOList.add(productInfoPO);
        }
        productInfoMapper.batchAddProductInfos(productInfoPOList);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult downloadProductTemplateExcel() {
        Resource resource = new ClassPathResource(TEMPLATE_PATH);
        return AjaxResult.success(resource);
    }
}
