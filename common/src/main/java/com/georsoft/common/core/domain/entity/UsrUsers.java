package com.georsoft.common.core.domain.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

import com.georsoft.common.annotation.Excel;
import com.georsoft.common.annotation.Excel.ColumnType;
import com.georsoft.common.annotation.Excel.Type;
import com.georsoft.common.annotation.Excels;
import com.georsoft.common.core.domain.BaseEntity;
import com.georsoft.common.xss.Xss;

/**
 * 用户对象 usr_users
 * 
 * @author douwenjie
 */
public class UsrUsers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", type = Type.EXPORT, cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long id;

    /** 用户账号 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户昵称 */
    @Excel(name = "登陆名称")
    private String loginName;

    /** 密码 */
    private String password;

    /** 创建时间 */
    private Date createDate;

    /** 可用时间 */
    private Date enableDate;

    /** 失效时间 */
    private Date expireDate;

    /** 手机号码 */
    @Excel(name = "手机号码", cellType = ColumnType.TEXT)
    private String mobileNo;

    /** 用户性别 */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /** 机构码 */
    @Excel(name = "机构码", type = Type.IMPORT)
    private String orgCode;

    /** 登陆状态（0正常 1停用） */
    @Excel(name = "登陆状态", readConverterExp = "0=正常,1=停用")
    private String loginStatus;

    /** 删除标志（0代表存在 2代表删除） */
    private String stopFlag;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String email;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginTime;

    /** 密码修改时间 */
    private String changePwd;

    /** 密码重试次数 */
    private String retryTime;

    /** 锁定状态（0正常 1锁定） */
    private String lockStatus;

    /** 机构分类 */
    private String orgClass;

    /** 中心标志 */
    private Boolean centerFlag;

    /** 登录次数 */
    private String loginNum;

    /** 登出时间 */
    private Date logoutTime;

    /** 登录索引 */
    private String viewIndex;

    /** 类型代码 */
    private String typeCode;

    private String optId;

    /** 身份证号 */
    private String idCardNo;

    /** 备注 */
    private String remark;

    /** 接受邮件 */
    private String acceptEmail;

    /** 接受短信 */
    private String acceptSms;

    /** 层级 */
    private String levelNo;

    /** 部门编号 */
    private String deptNo;

    /** 导入标识 */
    private String importFlag;

    /** 指纹标识 */
    private String fingerFlag;

    /** 全机构码 */
    private String fullOrgCode;

    /** 手机号码1 */
    private String mobileNo1;

    /** 融安登陆状态 */
    private String raLoginStatus;

    private String raComputerIp;

    /** 部分机构码 */
    private String partOrgCode;

    /** 机构对象 */
    @Excels({
        @Excel(name = "机构名称", targetAttr = "orgName", type = Type.EXPORT),
    })
    private UsrOrg org;

    /** 角色对象 */
    private List<UsrRole> roles;

    /** 角色组 */
    private String[] roleCodes;

    /** 岗位组 */
    private Long[] postIds;

    /** 头像 */
    private String avatar;
    public UsrUsers()
    {

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UsrUsers(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.loginName);
    }

    public static boolean isAdmin(String loginName)
    {
        return "admin".equals(loginName);
    }

    public String getOrgCode()
    {
        return orgCode;
    }

    public void setOrgCode(String orgCode)
    {
        this.orgCode = orgCode;
    }

    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLoginStatus()
    {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus)
    {
        this.loginStatus = loginStatus;
    }

    public String getStopFlag()
    {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag)
    {
        this.stopFlag = stopFlag;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public UsrOrg getOrg()
    {
        return org;
    }

    public void setOrg(UsrOrg org)
    {
        this.org = org;
    }

    public List<UsrRole> getRoles()
    {
        return roles;
    }

    public void setRoles(List<UsrRole> roles)
    {
        this.roles = roles;
    }

    public String[] getRoleCodes()
    {
        return roleCodes;
    }

    public void setRoleCodes(String[] roleCodes)
    {
        this.roleCodes = roleCodes;
    }

    public Long[] getPostIds()
    {
        return postIds;
    }

    public void setPostIds(Long[] postIds)
    {
        this.postIds = postIds;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getChangePwd() {
        return changePwd;
    }

    public void setChangePwd(String changePwd) {
        this.changePwd = changePwd;
    }

    public String getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(String retryTime) {
        this.retryTime = retryTime;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getOrgClass() {
        return orgClass;
    }

    public void setOrgClass(String orgClass) {
        this.orgClass = orgClass;
    }

    public Boolean getCenterFlag() {
        return centerFlag;
    }

    public void setCenterFlag(Boolean centerFlag) {
        this.centerFlag = centerFlag;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getViewIndex() {
        return viewIndex;
    }

    public void setViewIndex(String viewIndex) {
        this.viewIndex = viewIndex;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getOptId() {
        return optId;
    }

    public void setOptId(String optId) {
        this.optId = optId;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAcceptEmail() {
        return acceptEmail;
    }

    public void setAcceptEmail(String acceptEmail) {
        this.acceptEmail = acceptEmail;
    }

    public String getAcceptSms() {
        return acceptSms;
    }

    public void setAcceptSms(String acceptSms) {
        this.acceptSms = acceptSms;
    }

    public String getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(String levelNo) {
        this.levelNo = levelNo;
    }

    public String getImportFlag() {
        return importFlag;
    }

    public void setImportFlag(String importFlag) {
        this.importFlag = importFlag;
    }

    public String getFingerFlag() {
        return fingerFlag;
    }

    public void setFingerFlag(String fingerFlag) {
        this.fingerFlag = fingerFlag;
    }

    public String getFullOrgCode() {
        return fullOrgCode;
    }

    public void setFullOrgCode(String fullOrgCode) {
        this.fullOrgCode = fullOrgCode;
    }

    public String getMobileNo1() {
        return mobileNo1;
    }

    public void setMobileNo1(String mobileNo1) {
        this.mobileNo1 = mobileNo1;
    }

    public String getRaLoginStatus() {
        return raLoginStatus;
    }

    public void setRaLoginStatus(String raLoginStatus) {
        this.raLoginStatus = raLoginStatus;
    }

    public String getRaComputerIp() {
        return raComputerIp;
    }

    public void setRaComputerIp(String raComputerIp) {
        this.raComputerIp = raComputerIp;
    }

    public String getPartOrgCode() {
        return partOrgCode;
    }

    public void setPartOrgCode(String partOrgCode) {
        this.partOrgCode = partOrgCode;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "UsrUsers{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", createDate=" + createDate +
                ", enableDate=" + enableDate +
                ", expireDate=" + expireDate +
                ", mobileNo='" + mobileNo + '\'' +
                ", sex='" + sex + '\'' +
                ", orgCode=" + orgCode +
                ", loginStatus='" + loginStatus + '\'' +
                ", stopFlag='" + stopFlag + '\'' +
                ", email='" + email + '\'' +
                ", loginTime=" + loginTime +
                ", changePwd='" + changePwd + '\'' +
                ", retryTime='" + retryTime + '\'' +
                ", lockStatus='" + lockStatus + '\'' +
                ", orgClass='" + orgClass + '\'' +
                ", centerFlag=" + centerFlag +
                ", loginNum='" + loginNum + '\'' +
                ", logoutTime=" + logoutTime +
                ", viewIndex='" + viewIndex + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", optId='" + optId + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", remark='" + remark + '\'' +
                ", acceptEmail='" + acceptEmail + '\'' +
                ", acceptSms='" + acceptSms + '\'' +
                ", levelNo='" + levelNo + '\'' +
                ", deptNo='" + deptNo + '\'' +
                ", importFlag='" + importFlag + '\'' +
                ", fingerFlag='" + fingerFlag + '\'' +
                ", fullOrgCode='" + fullOrgCode + '\'' +
                ", mobileNo1='" + mobileNo1 + '\'' +
                ", raLoginStatus='" + raLoginStatus + '\'' +
                ", raComputerIp='" + raComputerIp + '\'' +
                ", partOrgCode='" + partOrgCode + '\'' +
                ", org=" + org +
                ", roles=" + roles +
                ", roleCodes=" + Arrays.toString(roleCodes) +
                ", postIds=" + Arrays.toString(postIds) +
                '}';
    }
}
