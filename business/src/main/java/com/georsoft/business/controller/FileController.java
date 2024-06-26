package com.georsoft.business.controller;

import com.georsoft.business.entity.VO.ProductInfoVO;
import com.georsoft.business.service.ProductFileService;
import com.georsoft.business.util.ExcelUtils;
import com.georsoft.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    ProductFileService productFileService;

    @PostMapping("/importProductExcel")
    public AjaxResult addImportExcel(@RequestParam(name = "file", required = true) MultipartFile file) {
        List<ProductInfoVO> list;
        try {
            list = ExcelUtils.read(file, ProductInfoVO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("发生错误,请检查文件是否正常");
        }
        if (CollectionUtils.isEmpty(list)) {
            return AjaxResult.error("上传文件不能为空");
        }
        return productFileService.addImportExcel(list);
    }

    @GetMapping("/downloadProductTemplateExcel")
    public AjaxResult downloadProductTemplateExcel() {
        return productFileService.downloadProductTemplateExcel();
    }

}
