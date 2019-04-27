package com.study.dispatcher.filter;

import com.study.dispatcher.DispatcherContext;
import com.study.dispatcher.exception.DefaultExceptionHandler;
import com.study.net.transport.TChannel;

import java.util.List;

/**
 * @author xiangdao
 */
public class DefaultChannelFilterChain implements ChannelFilterChain {

    /**
     *
     */
    private final EntryImp head;

    /**
     *
     */
    private final EntryImp tail;

    /**
     * 构造方法
     *
     * @param dispatcherContext
     */
    public DefaultChannelFilterChain(DispatcherContext dispatcherContext) {
        head = new EntryImp(new HeadFilter());
        tail = new EntryImp(new TailFilter(this, dispatcherContext.getDispatcher(),
                dispatcherContext.getChannelConnectListeners(), dispatcherContext.getExceptionHandlers()));
        List<ChannelFilter> channelFilters = dispatcherContext.getChannelFilterBuilder().getChannelFilters();
        EntryImp cur = head;
        for (ChannelFilter channelFilter : channelFilters) {
            EntryImp nextEntryImp = new EntryImp(channelFilter);
            cur.next = nextEntryImp;
            cur = nextEntryImp;
        }
        cur.next = tail;
    }

    @Override
    public void doFilterConnected(TChannel channel) {
        callNextConnected(head, channel);
    }

    @Override
    public void doFilterDisconnected(TChannel channel) {
        callNextDisconnected(head, channel);
    }

    @Override
    public void doFilterReceived(TChannel channel, Object message) {
        callNextReceived(head, channel, message);
    }

    @Override
    public void doFilterCaught(TChannel channel, Throwable e) {
        callNextCaught(head, channel, e);
    }

    /**
     * 执行下一链
     *
     * @param entry
     * @param channel
     * @throws Exception
     */
    private void callNextConnected(Entry entry, TChannel channel) {
        try {
            ChannelFilter channelFilter = entry.getChannelFilter();
            ChannelFilterChain chain = entry.getChannelFilterChain();
            channelFilter.doFilterConnected(channel, chain);
        } catch (Exception e) {
            doFilterCaught(channel, e);
        } catch (Error e) {
            doFilterCaught(channel, e);
            throw e;
        }
    }

    /**
     * 执行下一链
     *
     * @param entry
     * @param channel
     * @throws Exception
     */
    private void callNextDisconnected(Entry entry, TChannel channel) {
        try {
            ChannelFilter channelFilter = entry.getChannelFilter();
            ChannelFilterChain chain = entry.getChannelFilterChain();
            channelFilter.doFilterDisconnected(channel, chain);
        } catch (Exception e) {
            doFilterCaught(channel, e);
        } catch (Error e) {
            doFilterCaught(channel, e);
            throw e;
        }
    }

    /**
     * 执行下一链
     *
     * @param entry
     * @param channel
     * @param message
     * @throws Exception
     */
    private void callNextReceived(Entry entry, TChannel channel, Object message) {
        try {
            ChannelFilter channelFilter = entry.getChannelFilter();
            ChannelFilterChain chain = entry.getChannelFilterChain();
            channelFilter.doFilterReceived(channel, message, chain);
        } catch (Exception e) {
            doFilterCaught(channel, e);
        } catch (Error e) {
            doFilterCaught(channel, e);
            throw e;
        }
    }

    /**
     * 执行下一链
     *
     * @param entry
     * @param channel
     * @param e
     * @throws Exception
     */
    private void callNextCaught(Entry entry, TChannel channel, Throwable e) {
        try {
            ChannelFilter channelFilter = entry.getChannelFilter();
            ChannelFilterChain chain = entry.getChannelFilterChain();
            channelFilter.doFilterCaught(channel, e, chain);
        } catch (Exception ex) {
            DefaultExceptionHandler.INSTANCE.exceptionCaught(channel, e);
        } catch (Error ex) {
            DefaultExceptionHandler.INSTANCE.exceptionCaught(channel, e);
            throw ex;
        }
    }


    private class EntryImp implements Entry {

        private Entry next;

        private ChannelFilter channelFilter;

        private ChannelFilterChain channelFilterChain = new ChannelFilterChain() {

            @Override
            public void doFilterConnected(TChannel channel) {
                callNextConnected(next, channel);
            }

            @Override
            public void doFilterDisconnected(TChannel channel) {
                callNextDisconnected(next, channel);
            }

            @Override
            public void doFilterReceived(TChannel channel, Object message) {
                callNextReceived(next, channel, message);
            }

            @Override
            public void doFilterCaught(TChannel channel, Throwable exception) {
                callNextCaught(next, channel, exception);
            }
        };

        private EntryImp(ChannelFilter channelFilter) {
            this.channelFilter = channelFilter;
        }

        private EntryImp(ChannelFilter channelFilter, Entry next) {
            this.channelFilter = channelFilter;
            this.next = next;
        }

        @Override
        public ChannelFilter getChannelFilter() {
            return channelFilter;
        }

        @Override
        public ChannelFilterChain getChannelFilterChain() {
            return channelFilterChain;
        }
    }

}
