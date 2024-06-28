package com.georsoft.business.controller;

import com.georsoft.business.entity.VO.ProductInfoVO;
import com.georsoft.business.service.ProductFileService;
import com.georsoft.business.util.ExcelUtils;
import com.georsoft.common.core.domain.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    ProductFileService productFileService;

    @ApiOperation("通过Excel导入产品信息")
    @PostMapping("/importProductExcel")
    public AjaxResult addImportExcel(@RequestParam(name = "files", required = true) MultipartFile[] file) {
        List<ProductInfoVO> list = new ArrayList<>();
        try {
            for (MultipartFile multipartFile : file) {
                if (multipartFile.isEmpty()) {
                    return AjaxResult.error("文件不能为空");
                }
                list.addAll(ExcelUtils.read(multipartFile, ProductInfoVO.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("发生错误,请检查文件是否正常");
        }
        if (CollectionUtils.isEmpty(list)) {
            return AjaxResult.error("上传文件数据不能为空");
        }
        return productFileService.addImportExcel(list);
    }

//    @ApiOperation("下载产品信息模板(未使用)")
//    @GetMapping("/downloadProductTemplateExcel")
//    public AjaxResult downloadProductTemplateExcel() {
//        return productFileService.downloadProductTemplateExcel();
//    }

}
