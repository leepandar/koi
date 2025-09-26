package com.koi.common.log.support;

import com.koi.common.log.configuration.DiffLogProxyAutoConfiguration;
import com.koi.common.log.core.annotation.EnableDiffLog;
import com.koi.common.log.configuration.DiffLogProxyAutoConfiguration;
import com.koi.common.log.core.annotation.EnableDiffLog;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.lang.Nullable;

public class DiffLogConfigureSelector extends AdviceModeImportSelector<EnableDiffLog> {

    @Override
    @Nullable
    public String[] selectImports(AdviceMode adviceMode) {
        return switch (adviceMode) {
            case PROXY ->
                    new String[]{AutoProxyRegistrar.class.getName(), DiffLogProxyAutoConfiguration.class.getName()};
            case ASPECTJ -> new String[]{DiffLogProxyAutoConfiguration.class.getName()};
            default -> null;
        };
    }
}