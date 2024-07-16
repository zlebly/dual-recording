package com.georsoft.framework.web.service;

import com.georsoft.common.core.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.georsoft.common.constant.CacheConstants;
import com.georsoft.common.constant.Constants;
import com.georsoft.common.constant.UserConstants;
import com.georsoft.common.core.domain.entity.UsrUsers;
import com.georsoft.common.core.domain.model.RegisterBody;
import com.georsoft.common.exception.user.CaptchaException;
import com.georsoft.common.exception.user.CaptchaExpireException;
import com.georsoft.common.utils.MessageUtils;
import com.georsoft.common.utils.SecurityUtils;
import com.georsoft.common.utils.StringUtils;
import com.georsoft.framework.manager.AsyncManager;
import com.georsoft.framework.manager.factory.AsyncFactory;
import com.georsoft.system.service.ISysConfigService;
import com.georsoft.system.service.IUsrUserService;

/**
 * 注册校验方法
 * 
 * @author douwenjie
 */
@Component
public class SysRegisterService
{
    @Autowired
    private IUsrUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private CacheService cacheService;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
        UsrUsers usrUsers = new UsrUsers();
        usrUsers.setUserName(username);

        // 验证码开关
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled)
        {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (!userService.checkUserNameUnique(usrUsers))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            usrUsers.setLoginName(username);
            usrUsers.setPassword(SecurityUtils.encryptPassword(password));
            boolean regFlag = userService.registerUser(usrUsers);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = cacheService.getCacheObject(verifyKey);
        cacheService.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
