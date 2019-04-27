package com.leadDirec.dispatcher.factory;

import java.util.List;

public interface IBeanFactory {

    /**
     * 获取实例list
     *
     * @param clazz
     * @return beans
     */
    public <T> List<T> getBeans(Class<T> clazz);

    /**
     * 获取实例
     *
     * @param clazz
     * @return bean
     */
    public <T> T getBean(Class<T> clazz);
}
