package com.georsoft.system.service;

import java.util.List;
import com.georsoft.common.core.domain.entity.UsrUsers;

/**
 * 用户 业务层
 * 
 * @author douwenjie
 */
public interface IUsrUserService
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<UsrUsers> selectUserList(UsrUsers user);

    /**
     * 根据条件分页查询已分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<UsrUsers> selectAllocatedList(UsrUsers user);

    /**
     * 根据条件分页查询未分配用户角色列表
     * 
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<UsrUsers> selectUnallocatedList(UsrUsers user);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public UsrUsers selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public UsrUsers selectUserById(Long userId);

    /**
     * 根据用户ID查询用户所属角色组
     * 
     * @param userName 用户名
     * @return 结果
     */
    public String selectUserRoleGroup(String userName);

    /**
     * 根据用户ID查询用户所属岗位组
     * 
     * @param userName 用户名
     * @return 结果
     */
    public String selectUserPostGroup(String userName);

    /**
     * 校验用户名称是否唯一
     * 
     * @param user 用户信息
     * @return 结果
     */
    public boolean checkUserNameUnique(UsrUsers user);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public boolean checkPhoneUnique(UsrUsers user);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    public boolean checkEmailUnique(UsrUsers user);

    /**
     * 校验用户是否允许操作
     * 
     * @param user 用户信息
     */
    public void checkUserAllowed(UsrUsers user);

    /**
     * 校验用户是否有数据权限
     * 
     * @param userId 用户id
     */
    public void checkUserDataScope(Long userId);

    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(UsrUsers user);

    /**
     * 注册用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public boolean registerUser(UsrUsers user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(UsrUsers user);

    /**
     * 用户授权角色
     * 
     * @param userId 用户ID
     * @param roleCodes 角色组
     */
    public void insertUserAuth(Long userId, Long[] roleCodes);

    /**
     * 修改用户状态
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserStatus(UsrUsers user);

    /**
     * 修改用户基本信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUserProfile(UsrUsers user);

    /**
     * 修改用户头像
     * 
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public boolean updateUserAvatar(String userName, String avatar);

    /**
     * 重置用户密码
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int resetPwd(UsrUsers user);

    /**
     * 重置用户密码
     * 
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(String userName, String password);

    /**
     * 通过用户ID删除用户
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importUser(List<UsrUsers> userList, Boolean isUpdateSupport, String operName);
}
