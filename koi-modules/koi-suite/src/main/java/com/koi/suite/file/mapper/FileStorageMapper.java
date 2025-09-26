package com.koi.suite.file.mapper;

import com.koi.common.db.mybatisplus.ext.SuperMapper;
import com.koi.suite.file.domain.entity.FileStorage;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageMapper extends SuperMapper<FileStorage> {

}
