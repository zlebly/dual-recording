package com.georsoft.business.mapper;

import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.ProductInfoPO;

import java.util.List;

/**
 * 产品细心 数据层
 *
 * @author douwenjie
 */
public interface ProductInfoMapper {
    /**
     * 根据部分信息查询用户产品信息
     *
     * @param date 字典数据信息
     * @return 字典数据集合信息
     */
    List<ProductInfoPO> selectProductInfoList(QryProductInfoDTO date);

    /**
     * 更新产品信息
     * @param data 产品信息
     */
    void updateProductInfo(ProductInfoPO data);

    /**
     * 添加产品信息
     * @param data 产品信息
     */
    void addProductInfo(ProductInfoPO data);

    /**
     * 添加多条产品信息
     * @param data 产品信息
     */
    void batchAddProductInfos(List<ProductInfoPO> data);

    /**
     * 删除产品信息
     * @param id 产品id
     */
    void deleteProductInfo(String id);
}
