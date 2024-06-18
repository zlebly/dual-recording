package com.georsoft.business.service.Impl;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.mapper.ProductInfoMapper;
import com.georsoft.business.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ProductInfoPO> selectProductInfoList(QryProductInfoDTO data) {
        return productInfoMapper.selectProductInfoList(data);
    }
}
