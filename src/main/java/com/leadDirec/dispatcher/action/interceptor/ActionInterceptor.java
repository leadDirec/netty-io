package com.leadDirec.dispatcher.action.interceptor;


import com.leadDirec.dispatcher.action.ActionInvocation;

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



