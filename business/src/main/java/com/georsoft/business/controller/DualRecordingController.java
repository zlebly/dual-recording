package com.georsoft.business.controller;

import com.georsoft.business.entity.DTO.QryCustomerInfoDTO;
import com.georsoft.business.entity.DTO.QryProductInfoDTO;
import com.georsoft.business.entity.PO.CustomerInfoPO;
import com.georsoft.business.service.CustomerService;
import com.georsoft.common.core.controller.BaseController;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.georsoft.common.utils.PageUtils.startPage;

@RestController
@RequestMapping("/dualRecording")
public class DualRecordingController extends BaseController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getIdTypeOptions")
    public AjaxResult getIdTypeOptions() {
        // 调用服务层的方法获取数据字典列表
        return customerService.getDictDataList();
    }

    @GetMapping("/getUserIdOptions")
    public AjaxResult getUserIdOptions() {
        // 调用服务层的方法获取机构下的所有用户
        return customerService.getUserInfoByOrg();
    }

    @GetMapping("/getOrgCodeOptions")
    public AjaxResult getOrgCodeOptions() {
        // 调用服务层的方法获取机构及子机构信息
        return customerService.getCurrentAndSubOrg();
    }

    @GetMapping("/getCustomerInfo")
    public TableDataInfo getCustomerInfo(QryCustomerInfoDTO dto) {
        // 调用服务层的方法获取客户信息
        startPage();
        List<CustomerInfoPO> result = customerService.getCustomerInfo(dto);
        return getDataTable(result);
    }

    @PostMapping("/add")
    public AjaxResult addProductInfo(@RequestBody QryCustomerInfoDTO data) {
        customerService.addProductInfo(data);
        return success();
    }

    @PostMapping("/update")
    public AjaxResult updateProductInfoList(@RequestBody QryCustomerInfoDTO data) {
        customerService.updateProductInfo(data);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteProductInfoList(@PathVariable("id") String id) {
        customerService.deleteProductInfo(id);
        return success();
    }
}
