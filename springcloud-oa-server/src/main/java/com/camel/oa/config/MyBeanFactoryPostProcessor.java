package com.camel.oa.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    private static final String SERIALIZATION_ID = "4086d293-522c-4d25-8485-f1c1f5c09218";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        if ((configurableListableBeanFactory instanceof DefaultListableBeanFactory)) {
            DefaultListableBeanFactory dlbf = (DefaultListableBeanFactory) configurableListableBeanFactory;
            dlbf.setSerializationId(SERIALIZATION_ID);
        }
    }
}
