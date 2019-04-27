package com.leadDirec.dispatcher;

import com.leadDirec.dispatcher.action.ActionProxyFactory;
import com.leadDirec.dispatcher.action.ActionProxyFactoryBuilder;
import com.leadDirec.dispatcher.exception.ExceptionHandler;
import com.leadDirec.dispatcher.factory.IBeanFactory;
import com.leadDirec.dispatcher.factory.SpringBeanFactory;
import com.leadDirec.dispatcher.filter.ChannelFilterBuilder;
import com.leadDirec.dispatcher.filter.DefaultChannelFilterChain;
import com.leadDirec.dispatcher.impl.DefaultDispatcher;
import com.leadDirec.dispatcher.listener.ChannelConnectListener;
import com.leadDirec.net.transport.TChannelHandler;
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
