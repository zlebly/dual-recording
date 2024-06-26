package com.georsoft.business.entity.VO;

import com.georsoft.business.util.ExcelUtils.ExcelImport;
import lombok.Data;

@Data
public class ProductInfoVO {
    /**
     * 产品名称
     */
    @ExcelImport(name = "产品名称")
    private String productName;

    /**
     * 产品类型
     */
    @ExcelImport(name = "产品类型")
    private String productType;

    /**
     * 风险提示
     */
    @ExcelImport(name = "风险提示")
    private String riskTips;

    /**
     * 产品代码
     */
    @ExcelImport(name = "产品代码")
    private String productId;
}
