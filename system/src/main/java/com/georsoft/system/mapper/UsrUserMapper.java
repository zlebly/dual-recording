package com.georsoft.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.georsoft.common.core.domain.entity.UsrUsers;

/**
 * 用户表 数据层
 * 
 * @author douwenjie
 */
public interface UsrUserMapper
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param usrUsers 用户信息
     * @return 用户信息集合信息
     */
    public List<UsrUsers> selectUserList(UsrUsers usrUsers);

    /**
     * 根据条件分页查询已配用户角色列表
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
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(UsrUsers user);

    /**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(UsrUsers user);

    /**
     * 修改用户头像
     * 
     * @param userId 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userId") Long userId, @Param("avatar") String avatar);

    /**
     * 重置用户密码
     * 
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("userName") String userName, @Param("password") String password);

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
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名称
     * @return 结果
     */
    public UsrUsers checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param mobileNo 手机号码
     * @return 结果
     */
    public UsrUsers checkPhoneUnique(String mobileNo);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public UsrUsers checkEmailUnique(String email);

    /**
     * 根据用户id查询用户登陆名
     */
    public String selectLoginNameByUserId(Long userId);
}
