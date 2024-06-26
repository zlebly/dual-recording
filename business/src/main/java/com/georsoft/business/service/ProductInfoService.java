package com.georsoft.business.service;


import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;

import java.util.List;

/**
 * 产品信息管理 服务层
 *
 * @author douwenjie
 */
public interface ProductInfoService {
    /**
     * 查询产品信息列表
     * @param data 查询条件
     * @return 产品信息列表
     */
    List<ProductInfoPO> qryProductInfoList(QryProductInfoDTO data);

    /**
     * 添加产品信息
     * @param data 产品信息
     */
    void addProductInfo(QryProductInfoDTO data);

    /**
     * 修改产品信息
     * @param data 产品信息
     */
    void updateProductInfo(QryProductInfoDTO data);

    /**
     * 删除产品信息
     * @param id 产品信息id
     */
    void deleteProductInfo(String id);
}
