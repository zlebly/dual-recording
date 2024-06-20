package com.georsoft.business.service.Impl;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.mapper.ProductInfoMapper;
import com.georsoft.business.mapstruct.ProductConvertBasic;
import com.georsoft.business.service.ProductInfoService;
import com.georsoft.business.util.GenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoMapper productInfoMapper;

    /**
     * 查询产品信息列表
     *
     * @param data 查询条件
     * @return 产品信息列表
     */
    @Override
    public List<ProductInfoPO> qryProductInfoList(QryProductInfoDTO data) {
        return productInfoMapper.selectProductInfoList(data);
    }

    /**
     * 添加产品信息
     *
     * @param data 产品信息
     */
    @Override
    public void  addProductInfo(QryProductInfoDTO data) {
        ProductInfoPO productInfoPO = ProductConvertBasic.INSTANCE.toProductInfoPO(data);
        productInfoPO.setId(GenerateUtils.IdGenerate());
        productInfoPO.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        // TODO: 暂未添加创建人ID
        productInfoMapper.addProductInfo(productInfoPO);
    }

    /**
     * 修改产品信息
     *
     * @param data 产品信息
     */
    @Override
    public void updateProductInfo(QryProductInfoDTO data) {
        ProductInfoPO productInfoPO = ProductConvertBasic.INSTANCE.toProductInfoPO(data);
        productInfoMapper.updateProductInfo(productInfoPO);
    }

    /**
     * 删除产品信息
     *
     * @param id 产品信息id
     */
    @Override
    public void deleteProductInfo(Long id) {
        productInfoMapper.deleteProductInfo(id);
    }
}
