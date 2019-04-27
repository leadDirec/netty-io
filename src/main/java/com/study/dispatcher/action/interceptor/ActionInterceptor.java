package com.study.dispatcher.action.interceptor;


import com.study.dispatcher.action.ActionInvocation;

/**
 * action拦截器
 *
 * @author xiangdao
 *
 */
public interface ActionInterceptor {

    /**
     * 拦截
     *
     * @param actionInvocation
     */
    void intercept(ActionInvocation actionInvocation);

}



