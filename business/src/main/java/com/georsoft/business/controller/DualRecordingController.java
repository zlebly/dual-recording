package com.georsoft.business.controller;

import com.georsoft.business.entity.DTO.QryCustomerInfoDTO;
import com.georsoft.business.entity.PO.CustomerInfoPO;
import com.georsoft.business.service.CustomerService;
import com.georsoft.business.service.ReportService;
import com.georsoft.common.core.controller.BaseController;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.core.page.TableDataInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dualRecording")
public class DualRecordingController extends BaseController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ReportService reportService;

    @ApiOperation("获取证件类型列表")
    @GetMapping("/getIdTypeOptions")
    public AjaxResult getIdTypeOptions() {
        // 调用服务层的方法获取数据字典列表
        return customerService.getDictDataList();
    }

    @ApiOperation("获取当前机构下用户列表")
    @GetMapping("/getUserIdOptions")
    public AjaxResult getUserIdOptions() {
        // 调用服务层的方法获取机构下的所有用户
        return customerService.getUserInfoByOrg();
    }

    @ApiOperation("获取机构及子机构信息")
    @GetMapping("/getOrgCodeOptions")
    public AjaxResult getOrgCodeOptions() {
        // 调用服务层的方法获取机构及子机构信息
        return customerService.getCurrentAndSubOrg();
    }

    @ApiOperation("获取客户展示信息")
    @GetMapping("/getCustomerInfo")
    public TableDataInfo getCustomerInfo(QryCustomerInfoDTO dto) {
        // 调用服务层的方法获取客户信息
        startPage();
        List<CustomerInfoPO> result = customerService.getCustomerInfo(dto);
        return getDataTable(result);
    }

    @ApiOperation("新增客户信息")
    @PostMapping("/add")
    public AjaxResult addProductInfo(@RequestBody QryCustomerInfoDTO data) {
        customerService.addProductInfo(data);
        return success();
    }

    @ApiOperation("更新客户信息")
    @PostMapping("/update")
    public AjaxResult updateProductInfoList(@RequestBody QryCustomerInfoDTO data) {
        customerService.updateProductInfo(data);
        return success();
    }

    @ApiOperation("删除客户信息")
    @DeleteMapping("/delete/{id}")
    public AjaxResult deleteProductInfoList(@PathVariable("id") String id) {
        customerService.deleteProductInfo(id);
        return success();
    }

    @ApiOperation("获取机构用户的双录视频报表")
    @GetMapping("/getCustomerReport")
    public AjaxResult getCustomerReport(@RequestParam(value = "orgCode", required = false) String orgCode
            , @RequestParam(value = "userId", required = false) String userId) {
        startPage();
        return reportService.getCustomerReport(orgCode, userId);
    }

    @ApiOperation("获取机构的双录视频报表")
    @GetMapping("/getOrgReport")
    public AjaxResult getOrgReport(@RequestParam(value = "orgCode", required = false) String orgCode) {
        startPage();
        return reportService.getOrgReport(orgCode);
    }
}
