package com.koi.suite.file.event;

import com.koi.common.redis.listener.AbstractMessageEventListener;
import com.koi.suite.file.domain.constants.StorageConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.x.file.storage.core.FileStorageProperties;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.FileStorageServiceBuilder;
import org.dromara.x.file.storage.core.platform.FileStorage;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Redis存储配置
 *
 * @author lida
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RedisStorageSettingListener implements AbstractMessageEventListener<StorageSettingEvent> {

    private final FileStorageService fileStorageService;

    /**
     * fil
     * 处理Redis存储配置更新消息
     *
     * @param event Redis 存储配置事件
     */
    @Override
    public void handleMessage(StorageSettingEvent event) {
        log.info("租户ID:{}, 更新类型:{}", event.getTenantId(), event.getUpdateType());
        switch (event.getUpdateType()) {
            case 1 -> handleNewConfiguration(event);
            case 2 -> handleUpdateConfiguration(event);
            case 3 -> handleDeleteConfiguration(event);
            default -> log.warn("未知的更新类型: {}", event.getUpdateType());
        }
        // 当前的存储配置
        String platform = fileStorageService.getFileStorageList()
                .stream()
                .map(FileStorage::getPlatform)
                .collect(Collectors.joining(";"));
        log.info("- 更新后当前的存储配置平台： -{}", platform);
    }

    /**
     * 处理新的配置添加
     *
     * @param event Redis存储配置事件
     */
    private void handleNewConfiguration(StorageSettingEvent event) {
        log.info("新增类型 S3 文件上传 Bean Ref => {}", event.getPlatform());
        CopyOnWriteArrayList<FileStorage> list = fileStorageService.getFileStorageList();
        FileStorageProperties.AmazonS3Config amazonS3Config = createAmazonS3Config(event);
        list.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(Collections.singletonList(amazonS3Config), null));
    }

    /**
     * 处理配置更新
     *
     * @param event Redis存储配置事件
     */
    private void handleUpdateConfiguration(StorageSettingEvent event) {
        log.info("修改类型 S3 文件上传 Bean Ref => {}", event.getPlatform());
        CopyOnWriteArrayList<FileStorage> list = fileStorageService.getFileStorageList();
        FileStorage fileStorage = fileStorageService.getFileStorage(event.getPlatform());
        list.remove(fileStorage);
        FileStorageProperties.AmazonS3Config amazonS3Config = createAmazonS3Config(event);
        list.addAll(FileStorageServiceBuilder.buildAmazonS3FileStorage(Collections.singletonList(amazonS3Config), null));
    }

    /**
     * 处理配置删除
     * 删除事件无需更新redis，因为开启的配置不应该被删除
     *
     * @param event Redis存储配置事件
     */
    private void handleDeleteConfiguration(StorageSettingEvent event) {
        log.info("删除类型 S3 文件上传 Bean Ref => {}", event.getPlatform());
        CopyOnWriteArrayList<FileStorage> list = fileStorageService.getFileStorageList();
        FileStorage fileStorage = fileStorageService.getFileStorage(event.getPlatform());
        list.remove(fileStorage);
    }

    /**
     * 创建AmazonS3Config配置
     *
     * @param event Redis存储配置事件
     * @return 配置好的AmazonS3Config对象
     */
    private FileStorageProperties.AmazonS3Config createAmazonS3Config(StorageSettingEvent event) {
        FileStorageProperties.AmazonS3Config s3Config = new FileStorageProperties.AmazonS3Config();
        s3Config.setPlatform(event.getPlatform());
        s3Config.setAccessKey(event.getAccessKey());
        s3Config.setSecretKey(event.getSecretKey());
        s3Config.setRegion(event.getRegion());
        s3Config.setEndPoint(event.getEndPoint());
        s3Config.setBucketName(event.getBucketName());
        s3Config.setDomain(event.getDomain());
        s3Config.setBasePath(event.getBasePath());
        return s3Config;
    }

    @Override
    public Topic topic() {
        return new ChannelTopic(StorageConstants.STORAGE_CONFIG_EVENT_TOPIC);
    }

    @Override
    public Type type() {
        return StorageSettingEvent.class;
    }

}
