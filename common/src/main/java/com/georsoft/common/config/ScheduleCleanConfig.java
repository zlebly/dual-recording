package com.georsoft.common.config;

import com.georsoft.common.core.cache.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("'${cache.clean.enabled:false}'.equals('true')")
public class ScheduleCleanConfig {

    private final Logger logger = LoggerFactory.getLogger(ScheduleCleanConfig.class);

    @Autowired
    private CacheService cacheService;

    public ScheduleCleanConfig(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    /**
     *
     * 每周1的凌晨3点执行一次清理缓存
     */
    @Scheduled(cron = "${cache.clean.cron:0 0 3 ? * *}")
    public void clean() {
        logger.info("Cache clean start");
        cacheService.cleanCache();
        logger.info("Cache clean end");
    }
}