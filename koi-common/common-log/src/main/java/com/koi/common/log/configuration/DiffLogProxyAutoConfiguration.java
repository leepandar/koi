package com.koi.common.log.configuration;

import com.koi.common.log.service.IDiffLogPerformanceMonitor;
import com.koi.common.log.service.IDiffLogService;
import com.koi.common.log.service.IFunctionService;
import com.koi.common.log.service.IParseFunction;
import com.koi.common.log.service.impl.*;
import com.koi.common.log.service.impl.*;
import com.koi.common.log.support.aop.BeanFactoryDiffLogAdvisor;
import com.koi.common.log.support.aop.DiffLogInterceptor;
import com.koi.common.log.support.aop.DiffLogOperationSource;
import com.koi.common.log.DefaultDiffItemsToLogContentService;
import com.koi.common.log.IDiffItemsToLogContentService;
import com.koi.common.log.core.NoopJaversRepository;
import com.koi.common.log.core.annotation.EnableDiffLog;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

import static org.javers.core.diff.ListCompareAlgorithm.LEVENSHTEIN_DISTANCE;

/**
 * @author lida
 */
@Configuration
@EnableConfigurationProperties({DiffLogProperties.class})
@Slf4j
public class DiffLogProxyAutoConfiguration implements ImportAware {

    private AnnotationAttributes attributes;


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DiffLogOperationSource diffLogOperationSource() {
        return new DiffLogOperationSource();
    }

    @Bean
    @ConditionalOnMissingBean(IFunctionService.class)
    public IFunctionService functionService(ParseFunctionFactory parseFunctionFactory) {
        return new DefaultFunctionServiceImpl(parseFunctionFactory);
    }

    @Bean
    public ParseFunctionFactory parseFunctionFactory(@Autowired List<IParseFunction> parseFunctions) {
        return new ParseFunctionFactory(parseFunctions);
    }

    @Bean
    @ConditionalOnMissingBean(IParseFunction.class)
    public DefaultParseFunction parseFunction() {
        return new DefaultParseFunction();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Javers javers() {
        return JaversBuilder.javers()
                .registerJaversRepository(NoopJaversRepository.INSTANCE)
                .withListCompareAlgorithm(LEVENSHTEIN_DISTANCE)
                .build();
    }


    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public BeanFactoryDiffLogAdvisor diffLogAdvisor(DiffLogProperties diffLogProperties) {
        BeanFactoryDiffLogAdvisor advisor = new BeanFactoryDiffLogAdvisor();
        advisor.setDiffLogOperationSource(diffLogOperationSource());
        advisor.setAdvice(diffLogInterceptor(diffLogProperties));
        advisor.setOrder(attributes.getNumber("order"));
        return advisor;
    }

    @Bean
    @ConditionalOnMissingBean(IDiffLogPerformanceMonitor.class)
    public IDiffLogPerformanceMonitor diffLogPerformanceMonitor() {
        return new DefaultDiffLogPerformanceMonitor();
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DiffLogInterceptor diffLogInterceptor(DiffLogProperties properties) {
        DiffLogInterceptor interceptor = new DiffLogInterceptor();
        interceptor.setDiffLogOperationSource(diffLogOperationSource());
        interceptor.setServiceName(attributes.getString("serviceName"));
        interceptor.setJoinTransaction(attributes.getBoolean("joinTransaction"));
        interceptor.setDiffLog(properties.getDiffLog());
        interceptor.setDiffLogPerformanceMonitor(diffLogPerformanceMonitor());
        return interceptor;
    }

    @Bean
    public DiffParseFunction diffParseFunction(IDiffItemsToLogContentService diffItemsToLogContentService) {
        DiffParseFunction diffParseFunction = new DiffParseFunction();
        diffParseFunction.setDiffItemsToLogContentService(diffItemsToLogContentService);
        return diffParseFunction;
    }

    @Bean
    @Role(BeanDefinition.ROLE_APPLICATION)
    @ConditionalOnMissingBean(IDiffItemsToLogContentService.class)
    public IDiffItemsToLogContentService diffItemsToLogContentService(Javers javers, DiffLogProperties diffLogProperties) {
        return new DefaultDiffItemsToLogContentService(javers, diffLogProperties);
    }

    @Bean
    @Role(BeanDefinition.ROLE_APPLICATION)
    @ConditionalOnMissingBean(IDiffLogService.class)
    public IDiffLogService diffLogService() {
        return new DefaultDiffLogServiceImpl();
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.attributes = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableDiffLog.class.getName(), false));
        if (this.attributes == null) {
            log.info("EnableDiffLog is not present on importing class");
        }
    }
}
