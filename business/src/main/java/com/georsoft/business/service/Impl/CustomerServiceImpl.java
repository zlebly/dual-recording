package com.georsoft.business.service.Impl;

import com.georsoft.business.common.Constant;
import com.georsoft.business.entity.DTO.QryCustomerInfoDTO;
import com.georsoft.business.entity.PO.CustomerInfoPO;
import com.georsoft.business.entity.PO.CustomerPO;
import com.georsoft.business.entity.PO.DataDictPO;
import com.georsoft.business.entity.PO.ProductInfoPO;
import com.georsoft.business.entity.VO.DataDictOptionsVO;
import com.georsoft.business.entity.VO.OrgOptionsVO;
import com.georsoft.business.entity.VO.UserOptionsVO;
import com.georsoft.business.mapper.CustomerInfoMapper;
import com.georsoft.business.mapper.DictMapper;
import com.georsoft.business.mapper.OrgMapper;
import com.georsoft.business.mapper.UserMapper;
import com.georsoft.business.mapstruct.*;
import com.georsoft.business.service.CustomerService;
import com.georsoft.business.util.GenerateUtils;
import com.georsoft.common.core.domain.AjaxResult;
import com.georsoft.common.core.domain.entity.SysDept;
import com.georsoft.common.core.domain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    DictMapper dictMapper;

    @Autowired
    OrgMapper orgMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CustomerInfoMapper customerInfoMapper;

    @Override
    public AjaxResult getDictDataList() {
        List<DataDictPO> list = dictMapper.selectDictDataList(Constant.ID_TYPE);
        List<DataDictOptionsVO> result = new ArrayList<>();
        for (DataDictPO dataDictPO : list) {
            result.add(DictConvertBasic.toDataDictOptionsVO(dataDictPO));
        }
        if (CollectionUtils.isEmpty(result)) {
            return AjaxResult.error("数据为空");
        }
        return AjaxResult.success(result);
    }

    @Override
    public AjaxResult getCurrentAndSubOrg() {
        // TODO 暂传江苏省农村信用社联合社（全省汇总）
        List<SysDept> deptList = orgMapper.qryCurrentAndSubOrg("320000000");
        List<OrgOptionsVO> result = new ArrayList<>();
        for (SysDept dept : deptList) {
            result.add(OrgConvertBasic.toOrgOptionsVO(dept));
        }
        if (CollectionUtils.isEmpty(result)) {
            return AjaxResult.error("数据为空");
        }
        return AjaxResult.success(result);
    }

    @Override
    public AjaxResult getUserInfoByOrg() {
        // TODO 暂传江苏省农村信用社联合社（全省汇总）
        List<SysUser> userList = userMapper.qryUserInfoByOrg("320000000");
        List<UserOptionsVO> result = new ArrayList<>();
        for (SysUser user : userList) {
            result.add(UserConvertBasic.toUserOptionsVO(user));
        }
        if (CollectionUtils.isEmpty(result)) {
            return AjaxResult.error("数据为空");
        }
        return AjaxResult.success(result);
    }

    @Override
    public List<CustomerInfoPO> getCustomerInfo(QryCustomerInfoDTO qryCustomerInfoDTO) {
        return customerInfoMapper.qryCustomerInfo(qryCustomerInfoDTO);
    }

    @Override
    public void addProductInfo(QryCustomerInfoDTO data) {
        CustomerPO customerPO = CustomerConvert.convertToCustomerPO(data);
        customerPO.setId(GenerateUtils.IdGenerate());
        customerPO.setCreateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        // TODO: 暂未添加创建人ID, 默认使用admin的id和机构 部门
        customerPO.setUserId("1");
        customerPO.setOrgCode("320000000");
        customerPO.setDepNo("101");
        customerPO.setMarketNo("");
        customerPO.setMark("");
        customerPO.setImageNo("");
        customerPO.setAssociation("");
        customerPO.setAssoctationTime(new Date());
        customerInfoMapper.addCustomerInfo(customerPO);
    }

    @Override
    public void updateProductInfo(QryCustomerInfoDTO data) {
        CustomerPO customerPO = CustomerConvert.convertToCustomerPO(data);
        customerInfoMapper.updateCustomerInfo(customerPO);
    }

    @Override
    public void deleteProductInfo(String id) {
        customerInfoMapper.deleteCustomerInfo(id);
    }
}
