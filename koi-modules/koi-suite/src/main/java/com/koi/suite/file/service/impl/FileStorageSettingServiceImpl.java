package com.koi.suite.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.koi.common.core.BeanUtilPlus;
import com.koi.common.core.exception.CheckedException;
import com.koi.common.core.security.AuthenticationContext;
import com.koi.common.db.mybatisplus.ext.SuperServiceImpl;
import com.koi.common.db.mybatisplus.wrap.Wraps;
import com.koi.suite.file.domain.dto.req.FileStorageSettingPageReq;
import com.koi.suite.file.domain.dto.req.FileStorageSettingSaveReq;
import com.koi.suite.file.domain.dto.resp.FileStorageSettingPageResp;
import com.koi.suite.file.domain.entity.FileStorageSetting;
import com.koi.suite.file.event.StorageSettingTemplate;
import com.koi.suite.file.mapper.FileStorageSettingMapper;
import com.koi.suite.file.service.FileStorageSettingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FileStorageSettingServiceImpl extends SuperServiceImpl<FileStorageSettingMapper, FileStorageSetting> implements FileStorageSettingService {

    private final AuthenticationContext context;
    private final StorageSettingTemplate storageSettingTemplate;

    /**
     * 添加存储配置
     *
     * @param req 存储配置保存对象，包含要添加的存储配置信息
     */
    @Override
    public void create(FileStorageSettingSaveReq req) {
        // 获取当前租户ID
        Long tenantId = context.tenantId();
        // 检查平台名称是否已存在
        Long count = this.baseMapper.selectCount(Wraps.<FileStorageSetting>lbQ().eq(FileStorageSetting::getType, req.getType())
                .eq(FileStorageSetting::getBucketName, req.getBucketName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("令牌桶已存在");
        }
        // 复制属性并设置租户ID与平台名称
        FileStorageSetting setting = BeanUtil.toBean(req, FileStorageSetting.class);
        setting.setPlatform(StrUtil.join("-", req.getType(), req.getBucketName()));
        // 保存存储配置
        this.baseMapper.insert(setting);
        // 更新存储配置状态
        if (setting.getStatus()) {
            this.baseMapper.update(FileStorageSetting.builder().status(false).build(), Wraps.<FileStorageSetting>lbQ()
                    .ne(FileStorageSetting::getId, setting.getId()).eq(FileStorageSetting::getTenantId, tenantId));
        }
        this.storageSettingTemplate.publish(setting, 1);
    }

    /**
     * 删除配置
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        FileStorageSetting setting = Optional.ofNullable(this.baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("配置不存在"));
        if (setting.getStatus()) {
            throw CheckedException.badRequest("该平台名称已启用，无法删除");
        } else {
            this.removeById(id);
        }
        this.storageSettingTemplate.publish(setting, 3);
    }

    /**
     * 修改配置
     *
     * @param id
     * @param req
     */
    @Override
    public void modify(Long id, FileStorageSettingSaveReq req) {
        Optional.ofNullable(baseMapper.selectById(id)).orElseThrow(() -> CheckedException.notFound("配置不存在"));
        // 获取当前租户ID
        Long tenantId = context.tenantId();
        // 检查平台名称是否已存在
        Long count = this.baseMapper.selectCount(Wraps.<FileStorageSetting>lbQ().ne(FileStorageSetting::getId, id)
                .eq(FileStorageSetting::getType, req.getType()).eq(FileStorageSetting::getBucketName, req.getBucketName()));
        if (count != null && count > 0) {
            throw CheckedException.badRequest("令牌桶已存在");
        }
        FileStorageSetting bean = BeanUtilPlus.toBean(id, req, FileStorageSetting.class);
        bean.setTenantId(tenantId);
        bean.setPlatform(StrUtil.join("-", req.getType(), req.getBucketName()));
        // 更新存储配置状态
        if (req.getStatus()) {
            baseMapper.update(FileStorageSetting.builder().status(false).build(), Wraps.<FileStorageSetting>lbQ()
                    .ne(FileStorageSetting::getId, id).eq(FileStorageSetting::getTenantId, tenantId));
        }
        // 保存存储配置
        this.baseMapper.updateById(bean);
        this.storageSettingTemplate.publish(bean, 2);
    }

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    @Override
    public IPage<FileStorageSettingPageResp> pageList(FileStorageSettingPageReq req) {
        return this.baseMapper.selectPage(req.buildPage(), Wraps.<FileStorageSetting>lbQ()
                        .eq(FileStorageSetting::getStatus, req.getStatus())
                        .eq(FileStorageSetting::getType, req.getType()))
                .convert(x -> BeanUtil.toBean(x, FileStorageSettingPageResp.class));
    }
}
