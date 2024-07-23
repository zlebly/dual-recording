package com.georsoft.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.georsoft.common.core.domain.entity.UsrFunctionTree;

/**
 * 菜单表 数据层
 *
 * @author douwenjie
 */
public interface UsrFunctionTreeMapper
{
    /**
     * 查询系统菜单列表
     *
     * @param functionTree 菜单信息
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectFunctionList(UsrFunctionTree functionTree);

    /**
     * 根据用户所有权限
     *
     * @return 权限列表
     */
    public List<String> selectFunctionTreePerms();

    /**
     * 根据用户查询系统菜单列表
     *
     * @param functionTree 菜单信息
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectFunctionListByUserId(UsrFunctionTree functionTree);

    /**
     * 根据角色ID查询权限
     * 
     * @param roleCode 角色ID
     * @return 权限列表
     */
    public List<String> selectFunctionPermsByRoleCode(String roleCode);

    /**
     * 根据用户ID查询权限
     *
     * @param loginName 用户ID
     * @return 权限列表
     */
    public List<String> selectFunctionPermsByLoginName(String loginName);

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectFunctionTreeAll();

    /**
     * 根据用户登录名查询菜单
     *
     * @param loginName 用户登录名
     * @return 菜单列表
     */
    public List<UsrFunctionTree> selectFunctionTreeByLoginName(String loginName);

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleCode 角色ID
     * @return 选中菜单列表
     */
    public List<Long> selectFunctionListByRoleCode(@Param("roleCode") String roleCode);

    /**
     * 根据菜单ID查询信息
     *
     * @param functionCode 菜单ID
     * @return 菜单信息
     */
    public UsrFunctionTree selectFunctionById(String functionCode);

    /**
     * 是否存在菜单子节点
     *
     * @param functionCode 菜单ID
     * @return 结果
     */
    public int hasChildByFunctionCode(String functionCode);

    /**
     * 新增菜单信息
     *
     * @param functionTree 菜单信息
     * @return 结果
     */
    public int insertFunction(UsrFunctionTree functionTree);

    /**
     * 修改菜单信息
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
    public int deleteFunctionById(String functionCode);

    /**
     * 校验菜单名称是否唯一
     *
     * @param functionName 菜单名称
     * @param parentCode 父菜单ID
     * @return 结果
     */
    public UsrFunctionTree checkFunctionNameUnique(@Param("functionName") String functionName, @Param("parentCode") String parentCode);
}
