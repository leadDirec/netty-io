package com.study.dispatcher.filter;

import com.study.dispatcher.executor.NamedThreadFactory;
import com.study.net.transport.TChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author xiangdao
 * TODO: 此类实现应该交给具体的业务层实现
 */
public class ExecutorChannelFilter implements  ChannelFilter{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorChannelFilter.class);

    private Executor executor;

    private Executor singleExecutor;

    /**
     * 构造方法
     *
     * @param threads
     */
    public ExecutorChannelFilter(int threads) {
        this(threads, 5000);
    }

    /**
     * 构造方法
     *
     * @param threads
     * @param singlePoolInitCapacity
     */
    public ExecutorChannelFilter(int threads, final int singlePoolInitCapacity) {
        executor = Executors.newFixedThreadPool(threads, new NamedThreadFactory("ExecutorChannelFilter", true));
        singleExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(
                singlePoolInitCapacity), new NamedThreadFactory("SingleExecutorChannelFilter", true));
        ((ThreadPoolExecutor) singleExecutor).setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor exec) {
                LOGGER.error("singleExecutord处理队列已大于：" + singlePoolInitCapacity);
                executor.execute(r);
            }
        });
    }


    @Override
    public void doFilterConnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception {
        singleExecutor.execute(new Runnable() {
            public void run() {
                channelFilterChain.doFilterConnected(channel);
            }
        });
    }

    @Override
    public void doFilterDisconnected(TChannel channel, ChannelFilterChain channelFilterChain) throws Exception {
        singleExecutor.execute(new Runnable() {
            public void run() {
                channelFilterChain.doFilterDisconnected(channel);
            }
        });
    }

    @Override
    public void doFilterReceived(TChannel channel, Object message, ChannelFilterChain channelFilterChain) throws Exception {
        executor.execute(new Runnable() {
            public void run() {
                channelFilterChain.doFilterReceived(channel, message);
            }
        });
    }

    @Override
    public void doFilterCaught(TChannel channel, Throwable exception, ChannelFilterChain channelFilterChain) throws Exception {
        executor.execute(new Runnable() {
            public void run() {
                channelFilterChain.doFilterCaught(channel, exception);
            }
        });
    }
}
