package com.koi.suite.file.event;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.common.db.utils.TenantHelper;
import com.koi.suite.file.mapper.FileStorageSettingMapper;
import com.koi.suite.file.domain.constants.StorageConstants;
import com.koi.suite.file.domain.entity.FileStorageSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 存储配置模板
 *
 * @author lida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StorageSettingTemplate implements ApplicationRunner {

    private final AuthenticationContext context;
    private final FileStorageSettingMapper fileStorageSettingMapper;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        log.info("==================== 存储设置初始化-Begin ====================");
        List<FileStorageSetting> storageSettingList = TenantHelper.withIgnoreStrategy(() -> fileStorageSettingMapper.selectList(FileStorageSetting::getStatus, true));
        if (CollUtil.isEmpty(storageSettingList)) {
            return;
        }
        for (FileStorageSetting setting : storageSettingList) {
            publish(setting, 1);
        }
        log.info("==================== 存储设置初始化-End ====================");
    }

    public void publish(FileStorageSetting setting, int eventType) {
        log.info("redis publish - {},type -> {}", setting, eventType);
        // 构建后台存储配置的平台名称（租户ID + 平台名称）
        Long tenantId = setting.getTenantId();
        String platform = setting.getPlatform();
        // 构建并发布存储配置更新事件
        StorageSettingEvent event = new StorageSettingEvent();
        event.setPlatform(platform);
        event.setTenantId(tenantId);
        event.setUpdateType(eventType);
        event.setBucketName(setting.getBucketName());
        event.setAccessKey(setting.getAccessKey());
        event.setSecretKey(setting.getSecretKey());
        event.setRegion(setting.getRegion());
        event.setBasePath(setting.getBasePath());
        event.setDomain(setting.getDomain());
        event.setEndPoint(setting.getEndPoint());
        if (setting.getStatus()) {
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, tenantId.toString(), JSONObject.toJSONString(setting));
        }
        redisTemplate.convertAndSend(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC, event);
        SpringUtil.publishEvent(event);
    }

    public FileStorageSetting getDefaultStorageSetting() {
        String json = (String) redisTemplate.opsForHash().get(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString());
        if (StrUtil.isBlank(json)) {
            FileStorageSetting setting = this.fileStorageSettingMapper.selectOne(Wraps.<FileStorageSetting>lbQ().eq(FileStorageSetting::getStatus, true)
                    .eq(FileStorageSetting::getTenantId, context.tenantId()));
            redisTemplate.opsForHash().put(StorageConstants.STORAGE_SETTING_DEFAULT_SETTING, context.tenantId().toString(), JSONObject.toJSONString(setting));
            return setting;
        }
        return JSON.parseObject(json, FileStorageSetting.class);
    }
}
