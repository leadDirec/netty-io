package com.leadDirec.dispatcher.filter;

import com.leadDirec.common.dto.MessageBean;
import com.leadDirec.dispatcher.Dispatcher;
import com.leadDirec.dispatcher.exception.DefaultExceptionHandler;
import com.leadDirec.dispatcher.exception.ExceptionHandler;
import com.leadDirec.dispatcher.listener.ChannelConnectListener;
import com.leadDirec.net.transport.TChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiangdao
 */
public class TailFilter implements ChannelFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TailFilter.class);

    private ChannelConnectListener[] channelListeners;

    private ExceptionHandler[] exceptionHandlers;

    private Dispatcher dispatcher;

    private ChannelFilterChain defaultChannelFilterChain;

    TailFilter(ChannelFilterChain defaultChannelFilterChain, Dispatcher dispatcher,
               ChannelConnectListener[] channelListeners, ExceptionHandler[] exceptionHandlers) {
        this.defaultChannelFilterChain = defaultChannelFilterChain;
        this.dispatcher = dispatcher;
        this.channelListeners = channelListeners;
        if (this.channelListeners == null) {
            this.channelListeners = new ChannelConnectListener[0];
        }
        this.exceptionHandlers = exceptionHandlers;
        if (this.exceptionHandlers == null) {
            this.exceptionHandlers = new ExceptionHandler[0];
        }
    }

    @Override
    public void doFilterConnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception {
        if (channelListeners.length > 0) {
            for (ChannelConnectListener channelListener : channelListeners) {
                try {
                    channelListener.connected(channel);
                } catch (Exception e) {
                    defaultChannelFilterChain.doFilterCaught(channel, e);
                } catch (Error e) {
                    defaultChannelFilterChain.doFilterCaught(channel, e);
                    throw e;
                }
            }
        }
    }

    @Override
    public void doFilterDisconnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception {
        if (channelListeners.length > 0) {
            for (ChannelConnectListener channelListener : channelListeners) {
                try {
                    channelListener.disconnected(channel);
                } catch (Exception e) {
                    defaultChannelFilterChain.doFilterCaught(channel, e);
                } catch (Error e) {
                    defaultChannelFilterChain.doFilterCaught(channel, e);
                    throw e;
                }
            }
        }
    }

    @Override
    public void doFilterReceived(TChannel channel, Object message, ChannelFilterChain channelFilterChain) throws Exception {
        dispatcher.dispatch(channel, (MessageBean) message);
    }

    @Override
    public void doFilterCaught(TChannel channel, Throwable exception, ChannelFilterChain channelFilterChain) throws Exception {
        if (exceptionHandlers.length > 0) {
            for (ExceptionHandler exceptionHandler : exceptionHandlers) {
                try {
                    exceptionHandler.exceptionCaught(channel, exception);
                } catch (Exception e) {
                    DefaultExceptionHandler.INSTANCE.exceptionCaught(channel, exception);
                } catch (Error e) {
                    DefaultExceptionHandler.INSTANCE.exceptionCaught(channel, exception);
                    throw e;
                }
            }
        } else {
            DefaultExceptionHandler.INSTANCE.exceptionCaught(channel, exception);
        }
    }
}
