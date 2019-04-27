package com.study.dispatcher.action;

public interface ActionInvocation {

    /**
     * 获取action实例
     *
     * @return action
     */
    Object getAction();

    /**
     * 获取action代理
     *
     * @return actionProxy
     */
    Object getActionProxy();

    /**
     * 调用
     */
    public void invoke();

}
