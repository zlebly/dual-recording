package com.georsoft.framework.web.service;

import java.util.concurrent.TimeUnit;

import com.georsoft.common.core.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.georsoft.common.constant.CacheConstants;
import com.georsoft.common.core.domain.entity.UsrUsers;
import com.georsoft.common.exception.user.UserPasswordNotMatchException;
import com.georsoft.common.exception.user.UserPasswordRetryLimitExceedException;
import com.georsoft.common.utils.SecurityUtils;
import com.georsoft.framework.security.context.AuthenticationContextHolder;

/**
 * 登录密码方法
 * 
 * @author douwenjie
 */
@Component
public class SysPasswordService
{
    @Autowired
    private CacheService cacheService;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    /**
     * 登录账户密码错误次数缓存键名
     * 
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username)
    {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    public void validate(UsrUsers user)
    {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();

        Integer retryCount = cacheService.getCacheObject(getCacheKey(username));

        if (retryCount == null)
        {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount).intValue())
        {
            throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
        }

        if (!matches(user, password))
        {
            retryCount = retryCount + 1;
            cacheService.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(UsrUsers user, String rawPassword)
    {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName)
    {
        if (cacheService.hasKey(getCacheKey(loginName)))
        {
            cacheService.deleteObject(getCacheKey(loginName));
        }
    }
}
