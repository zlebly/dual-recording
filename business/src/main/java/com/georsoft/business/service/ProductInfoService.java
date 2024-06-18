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
    List<ProductInfoPO> selectProductInfoList(QryProductInfoDTO data);
}
