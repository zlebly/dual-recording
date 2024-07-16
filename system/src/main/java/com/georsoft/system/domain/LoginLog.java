package com.georsoft.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.georsoft.common.annotation.Excel;
import com.georsoft.common.annotation.Excel.ColumnType;
import com.georsoft.common.core.domain.BaseEntity;

/**
 * 系统访问记录表 log_login_log
 * 
 * @author douwenjie
 */
public class LoginLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "序号", cellType = ColumnType.NUMERIC)
    private Long id;

    /** 登录状态 0成功 1失败 */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    private String loginFlag;

    /** 登录IP地址 */
    @Excel(name = "登录地址")
    private String clientIp;

    /** 客户端名 */
    @Excel(name = "客户端名")
    private String clientName;

    /** 操作账号 */
    @Excel(name = "操作账号")
    private String operator;

    /** 提示消息 */
    @Excel(name = "提示消息")
    private String remark;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getLoginFlag()
    {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag)
    {
        this.loginFlag = loginFlag;
    }

    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Date getOperateTime()
    {
        return operateTime;
    }

    public void setOperateTime(Date operateTime)
    {
        this.operateTime = operateTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
