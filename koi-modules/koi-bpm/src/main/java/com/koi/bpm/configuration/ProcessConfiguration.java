package com.koi.bpm.configuration;

import org.camunda.bpm.model.bpmn.impl.BpmnParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * process配置
 *
 * @author lida
 */

@Configuration
public class ProcessConfiguration {

    @Bean
    public BpmnParser bpmnParser() {
        return new BpmnParser();
    }

    @Bean("wp")
    public CamundaWpParse camundaWpParse() {
        return new CamundaWpParse();
    }
}
