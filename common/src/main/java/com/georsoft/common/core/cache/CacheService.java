package com.georsoft.common.core.cache;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public interface CacheService {
    Integer DEFAULT_CAPACITY = 50000;

    <T> void setCacheObject(final String key, final T value);

    <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit);

    Boolean hasKey(String key);

    <T> T getCacheObject(final String key);

    boolean deleteObject(final String key);

    boolean deleteObject(final Collection collection);

    Collection<String> keys(final String pattern);

    void cleanCache();
}