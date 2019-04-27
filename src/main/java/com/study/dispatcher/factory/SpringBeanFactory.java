package com.study.dispatcher.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class SpringBeanFactory implements IBeanFactory {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBeanFactory.class);

    /**
     * applicationContext
     */
    private final ApplicationContext applicationContext;

    /**
     * 构造方法
     *
     * @param applicationContext
     */
    public SpringBeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public <T> List<T> getBeans(Class<T> clazz) {
        Collection<T> c = applicationContext.getBeansOfType(clazz).values();
        if (c != null && !c.isEmpty()) {
            return new ArrayList<T>(c);
        }
        return Collections.emptyList();
    }

    @Override
    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
