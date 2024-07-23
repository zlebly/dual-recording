package com.georsoft.common.core.domain.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 菜单权限表 usr_function_tree
 * 
 * @author douwenjie
 */
public class UsrFunctionTree
{
    /** 菜单ID */
    private String functionCode;

    /** 菜单名称 */
    private String functionName;

    /** 父菜单ID */
    private String parentCode;

    /** 显示顺序 */
    private Integer viewIndex;

    /** 是否叶子节点 */
    private Boolean isLeaf;

    /** 功能路径 */
    private String functionPath;

    /** 菜单备注 */
    private String remark;

    /** 是否面板（0是 1否） */
    private String isPanel;

    /** 菜单图标 */
    private String iconResource;

    /** 类型（M目录 C菜单 F按钮） */
    private String functionType;

    /** 显示状态（0显示 1隐藏） */
    private String viewFlag;

    /** 功能类 */
    private String functionClass;

    /** 完整路径 */
    private String fullPath;

    /** 功能类别 */
    private String functionKind;

    /** 快捷方式 */
    private String quickType;

    /** 路由参数 */
    private String query;

    /** 是否为外链（0是 1否） */
    private String isFrame;

    /** 是否缓存（0 缓存 1 不缓存） */
    private String isCache;

    /** 路由路径 */
    private String path;

    /** 组件路径 */
    private String component;

    /** 权限字符串 */
    private String perms;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, Object> params;

    /** 子菜单 */
    private List<UsrFunctionTree> children = new ArrayList<UsrFunctionTree>();

    public String getFunctionCode()
    {
        return functionCode;
    }

    public void setFunctionCode(String functionCode)
    {
        this.functionCode = functionCode;
    }

    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    public String getFunctionName()
    {
        return functionName;
    }

    public void setFunctionName(String functionName)
    {
        this.functionName = functionName;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getViewIndex()
    {
        return viewIndex;
    }

    public void setViewIndex(Integer viewIndex)
    {
        this.viewIndex = viewIndex;
    }

    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    public String getComponent()
    {
        return component;
    }

    public void setComponent(String component)
    {
        this.component = component;
    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public String getIsFrame()
    {
        return isFrame;
    }

    public void setIsFrame(String isFrame)
    {
        this.isFrame = isFrame;
    }

    public String getIsCache()
    {
        return isCache;
    }

    public void setIsCache(String isCache)
    {
        this.isCache = isCache;
    }

    @NotBlank(message = "菜单类型不能为空")
    public String getFunctionType()
    {
        return functionType;
    }

    public void setFunctionType(String functionType)
    {
        this.functionType = functionType;
    }

    public String getViewFlag()
    {
        return viewFlag;
    }

    public void setViewFlag(String viewFlag)
    {
        this.viewFlag = viewFlag;
    }

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    public String getPerms()
    {
        return perms;
    }

    public void setPerms(String perms)
    {
        this.perms = perms;
    }

    public String getIconResource()
    {
        return iconResource;
    }

    public void setIconResource(String iconResource)
    {
        this.iconResource = iconResource;
    }

    public List<UsrFunctionTree> getChildren()
    {
        return children;
    }

    public void setChildren(List<UsrFunctionTree> children)
    {
        this.children = children;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsPanel() {
        return isPanel;
    }

    public void setIsPanel(String isPanel) {
        this.isPanel = isPanel;
    }

    public String getFunctionClass() {
        return functionClass;
    }

    public void setFunctionClass(String functionClass) {
        this.functionClass = functionClass;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFunctionKind() {
        return functionKind;
    }

    public void setFunctionKind(String functionKind) {
        this.functionKind = functionKind;
    }

    public String getQuickType() {
        return quickType;
    }

    public void setQuickType(String quickType) {
        this.quickType = quickType;
    }

    @Override
    public String toString() {
        return "UsrFunctionTree{" +
                "functionCode=" + functionCode +
                ", functionName='" + functionName + '\'' +
                ", parentCode=" + parentCode +
                ", viewIndex=" + viewIndex +
                ", isLeaf=" + isLeaf +
                ", functionPath='" + functionPath + '\'' +
                ", remark='" + remark + '\'' +
                ", isPanel='" + isPanel + '\'' +
                ", iconResource='" + iconResource + '\'' +
                ", functionType='" + functionType + '\'' +
                ", viewFlag='" + viewFlag + '\'' +
                ", functionClass='" + functionClass + '\'' +
                ", fullPath='" + fullPath + '\'' +
                ", functionKind='" + functionKind + '\'' +
                ", quickType='" + quickType + '\'' +
                ", query='" + query + '\'' +
                ", isFrame='" + isFrame + '\'' +
                ", isCache='" + isCache + '\'' +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", perms='" + perms + '\'' +
                ", params=" + params +
                ", children=" + children +
                '}';
    }
}