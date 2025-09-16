package com.koi.suite.file.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.file.domain.entity.FileStorageSetting;
import org.springframework.stereotype.Repository;

/**
 * @author lida
 */
@Repository
public interface FileStorageSettingMapper extends SuperMapper<FileStorageSetting> {

}
