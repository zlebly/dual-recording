package com.georsoft.system.service;

import java.util.List;
import java.util.Set;
import com.georsoft.common.core.domain.TreeSelect;
import com.georsoft.common.core.domain.entity.UsrFunctionTree;
import com.georsoft.system.domain.vo.RouterVo;

/**
 * 菜单 业务层
 * 
 * @author douwenjie
 */
public interface IUsrFunctionTreeService
{
    /**
     * 根据用户查询系统菜单列表
     * 
     * @param id 用户ID
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectFunctionList(Long id);

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param functionTree 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectFunctionList(UsrFunctionTree functionTree, Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param loginName 用户ID
     * @return 权限列表
     */
    public Set<String> selectFunctionPermsByUserId(String loginName);

    /**
     * 根据角色ID查询权限
     * 
     * @param roleCode 角色ID
     * @return 权限列表
     */
    public Set<String> selectFunctionPermsByRoleCode(Long roleCode);

    /**
     * 根据用户登陆名查询菜单树信息
     * 
     * @param loginName 用户登陆名
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectFunctionTreeByUserId(String loginName);

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleCode 角色ID
     * @return 选中菜单列表
     */
    public List<Long> selectFunctionListByRoleCode(Long roleCode);

    /**
     * 构建前端路由所需要的菜单
     * 
     * @param functionTrees 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildFunctionTrees(List<UsrFunctionTree> functionTrees);

    /**
     * 构建前端所需要树结构
     * 
     * @param functionTrees 菜单列表
     * @return 树结构列表
     */
    public List<UsrFunctionTree> buildFunctionTree(List<UsrFunctionTree> functionTrees);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param functionTrees 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildFunctionTreeSelect(List<UsrFunctionTree> functionTrees);

    /**
     * 根据菜单ID查询信息
     * 
     * @param functionCode 菜单ID
     * @return 菜单信息
     */
    public UsrFunctionTree selectFunctionById(Long functionCode);

    /**
     * 是否存在菜单子节点
     * 
     * @param functionCode 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean hasChildByFunctionCode(Long functionCode);

    /**
     * 查询菜单是否存在角色
     * 
     * @param functionCode 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkFunctionExistRole(Long functionCode);

    /**
     * 新增保存菜单信息
     * 
     * @param functionTree 菜单信息
     * @return 结果
     */
    public int insertFunction(UsrFunctionTree functionTree);

    /**
     * 修改保存菜单信息
     * 
     * @param functionTree 菜单信息
     * @return 结果
     */
    public int updateFunction(UsrFunctionTree functionTree);

    /**
     * 删除菜单管理信息
     * 
     * @param functionCode 菜单ID
     * @return 结果
     */
    public int deleteFunctionById(Long functionCode);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param functionTree 菜单信息
     * @return 结果
     */
    public boolean checkFunctionNameUnique(UsrFunctionTree functionTree);
}
