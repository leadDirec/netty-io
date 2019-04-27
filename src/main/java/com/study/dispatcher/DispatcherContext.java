package com.study.dispatcher;

import com.study.dispatcher.action.ActionProxyFactory;
import com.study.dispatcher.action.ActionProxyFactoryBuilder;
import com.study.dispatcher.exception.ExceptionHandler;
import com.study.dispatcher.factory.IBeanFactory;
import com.study.dispatcher.factory.SpringBeanFactory;
import com.study.dispatcher.filter.ChannelFilterBuilder;
import com.study.dispatcher.filter.DefaultChannelFilterChain;
import com.study.dispatcher.impl.DefaultDispatcher;
import com.study.dispatcher.listener.ChannelConnectListener;
import com.study.net.transport.TChannelHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * @author xiangdao
 */
public class DispatcherContext implements ApplicationContextAware,FactoryBean<TChannelHandler> {

    private IBeanFactory iBeanFactory;

    private List<String> packageNames;

    private Dispatcher dispatcher;

    private DispatcherChannelHandler dispatcherChannelHandler;

    private ChannelFilterBuilder channelFilterBuilder;

    /**
     * 构造方法
     *
     * @param packageNames
     */
    public DispatcherContext(List<String> packageNames) {
        this.packageNames = packageNames;
    }


    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public ChannelConnectListener[] getChannelConnectListeners() {
        List<ChannelConnectListener> channelConnectListeners = iBeanFactory.getBeans(ChannelConnectListener.class);
        if (channelConnectListeners == null || channelConnectListeners.isEmpty()) {
            return new ChannelConnectListener[0];
        }
        return channelConnectListeners.toArray(new ChannelConnectListener[channelConnectListeners.size()]);
    }

    public ExceptionHandler[] getExceptionHandlers() {
        List<ExceptionHandler> exceptionHandlers = iBeanFactory.getBeans(ExceptionHandler.class);
        if (exceptionHandlers == null || exceptionHandlers.isEmpty()) {
            return new ExceptionHandler[0];
        }
        return exceptionHandlers.toArray(new ExceptionHandler[exceptionHandlers.size()]);
    }

    public ChannelFilterBuilder getChannelFilterBuilder() {
        return iBeanFactory.getBean(ChannelFilterBuilder.class);
    }

    @Override
    public TChannelHandler getObject() throws Exception {
        if (dispatcherChannelHandler == null) {
            dispatcherChannelHandler = new DispatcherChannelHandler(new DefaultChannelFilterChain(this));
        }
        return dispatcherChannelHandler;
    }

    @Override
    public Class<?> getObjectType() {
        return TChannelHandler.class;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        iBeanFactory = new SpringBeanFactory(applicationContext);
        ActionProxyFactoryBuilder builder;
        try {
            builder = new ActionProxyFactoryBuilder(packageNames, iBeanFactory);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        ActionProxyFactory actionProxyFactory = builder.build();
        dispatcher = new DefaultDispatcher(actionProxyFactory);
    }

    public boolean isSingleton() {
        return true;
    }
}
