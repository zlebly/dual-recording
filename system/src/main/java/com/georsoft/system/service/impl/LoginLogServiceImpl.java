package com.georsoft.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.georsoft.system.domain.LoginLog;
import com.georsoft.system.mapper.LoginLogMapper;
import com.georsoft.system.service.ILoginLogService;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author douwenjie
 */
@Service
public class LoginLogServiceImpl implements ILoginLogService
{

    @Autowired
    private LoginLogMapper logininforMapper;

    /**
     * 新增系统登录日志
     * 
     * @param log 访问日志对象
     */
    @Override
    public void insertLoginLog(LoginLog log)
    {
        logininforMapper.insertLoginLog(log);
    }

    /**
     * 查询系统登录日志集合
     * 
     * @param log 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<LoginLog> selectLogininforList(LoginLog log)
    {
        return logininforMapper.selectLoginLogList(log);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLoginLogByIds(Long[] infoIds)
    {
        return logininforMapper.deleteLoginLogByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginLog()
    {
        logininforMapper.cleanLoginLog();
    }
}
