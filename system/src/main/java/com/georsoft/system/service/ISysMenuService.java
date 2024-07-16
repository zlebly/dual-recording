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
public interface ISysMenuService
{
    /**
     * 根据用户查询系统菜单列表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectMenuList(Long userId);

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectMenuList(UsrFunctionTree menu, Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     * 
     * @param roleCode 角色ID
     * @return 权限列表
     */
    public Set<String> selectMenuPermsByRoleCode(Long roleCode);

    /**
     * 根据用户ID查询菜单树信息
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleCode 角色ID
     * @return 选中菜单列表
     */
    public List<Long> selectMenuListByRoleCode(Long roleCode);

    /**
     * 构建前端路由所需要的菜单
     * 
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<UsrFunctionTree> menus);

    /**
     * 构建前端所需要树结构
     * 
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<UsrFunctionTree> buildMenuTree(List<UsrFunctionTree> menus);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildMenuTreeSelect(List<UsrFunctionTree> menus);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public UsrFunctionTree selectMenuById(Long menuId);

    /**
     * 是否存在菜单子节点
     * 
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean hasChildByMenuId(Long menuId);

    /**
     * 查询菜单是否存在角色
     * 
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkMenuExistRole(Long menuId);

    /**
     * 新增保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(UsrFunctionTree menu);

    /**
     * 修改保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(UsrFunctionTree menu);

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean checkMenuNameUnique(UsrFunctionTree menu);
}
