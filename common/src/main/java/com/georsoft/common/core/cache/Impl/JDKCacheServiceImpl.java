package com.georsoft.common.core.cache.Impl;

import com.georsoft.common.core.cache.CacheService;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;
import io.jsonwebtoken.lang.Collections;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于JDK的Cache实现, 使用优化缓存的ConcurrentLinkedHashMap
 */
@Service
@ConditionalOnExpression("'${cache.type:jdk}'.equals('jdk')")
public class JDKCacheServiceImpl implements CacheService {
    private Map<String, Object> cacheMap;

    private Map<String, Long> expireMap;

    @PostConstruct
    public void initCache() {
        initCacheMap();
        initCacheEntryMap();
    }

    public void initCacheMap() {
        cacheMap = new ConcurrentLinkedHashMap.Builder<String, Object>()
                .maximumWeightedCapacity(CacheService.DEFAULT_CAPACITY)
                .weigher(Weighers.singleton())
                .build();
    }

    public void initCacheEntryMap() {
        expireMap = new ConcurrentLinkedHashMap.Builder<String, Long>()
                .maximumWeightedCapacity(CacheService.DEFAULT_CAPACITY)
                .weigher(Weighers.singleton())
                .build();
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     */
    @Override
    public <T> void setCacheObject(final String key, final T value) {
        cacheMap.put(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     */
    @Override
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        long expireTime = System.currentTimeMillis() + timeUnit.toMillis(timeout);
        cacheMap.put(key, value);
        expireMap.put(key, expireTime);
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @Override
    public Boolean hasKey(final String key) {
        if (cacheMap.containsKey(key)) {
            if (expireMap.containsKey(key)) {
                Long expireTime = expireMap.get(key);
                if (expireTime != null && expireTime > System.currentTimeMillis()) {
                    return true;
                } else {
                    cacheMap.remove(key);
                    expireMap.remove(key);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    @Override
    public <T> T getCacheObject(final String key) {
        if (cacheMap.containsKey(key)) {
            if (expireMap.containsKey(key)) {
                Long expireTime = expireMap.get(key);
                if (expireTime != null && expireTime > System.currentTimeMillis()) {
                    return (T) cacheMap.get(key);
                } else {
                    cacheMap.remove(key);
                    expireMap.remove(key);
                }
            } else {
                return (T) cacheMap.get(key);
            }
        }
        return null;
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    @Override
    public boolean deleteObject(final String key) {
        if (cacheMap.containsKey(key)) {
            if (expireMap.containsKey(key)) {
                expireMap.remove(key);
            }
            cacheMap.remove(key);
            return true;
        }
        return false;
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    @Override
    public boolean deleteObject(final Collection collection) {
        if (Collections.isEmpty(collection)) {
            return false;
        }
        for (Object key : collection) {
            deleteObject((String)key);
        }
        return true;
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    @Override
    public Collection<String> keys(final String pattern) {
        Collection<String> matchesCollection = new HashSet<>();
        for(String key : cacheMap.keySet()) {
            if (key.matches(pattern)) {
                if (expireMap.containsKey(key)) {
                    Long expireTime = expireMap.get(key);
                    if (expireTime != null && expireTime > System.currentTimeMillis()) {
                        matchesCollection.add(key);
                    } else {
                        cacheMap.remove(key);
                        expireMap.remove(key);
                    }
                } else {
                    matchesCollection.add(key);
                }
            }
        }
        return matchesCollection;
    }

    @Override
    public void cleanCache() {
        for (String key : cacheMap.keySet()) {
            if (expireMap.containsKey(key)) {
                Long expireTime = expireMap.get(key);
                if (expireTime == null || expireTime <= System.currentTimeMillis()) {
                    cacheMap.remove(key);
                    expireMap.remove(key);
                }
            }
        }
    }
}
