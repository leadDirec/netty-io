package com.study.dispatcher.action;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangdao
 */
public class ActionContext {

    /**
     * 线程上下文
     */
    private static final ThreadLocal<ActionContext> ACTION_CONTEXT_THREAD_LOCAL = new ThreadLocal<ActionContext>();

    /**
     * 附件
     */
    private Map<String, Object> atte = new HashMap<String, Object>();

    /**
     * 获取ActionContext
     *
     * @return ActionContext
     */
    static ActionContext getActionContext() {
        ActionContext actionContext = ACTION_CONTEXT_THREAD_LOCAL.get();
        if (actionContext == null) {
            actionContext = new ActionContext();
            ACTION_CONTEXT_THREAD_LOCAL.set(actionContext);
        }
        return actionContext;
    }

    /**
     * 私有构造方法
     */
    private ActionContext() {
    }

    /**
     * 重置
     */
    public void rest() {
        ACTION_CONTEXT_THREAD_LOCAL.remove();
    }

    /**
     * 设置附件
     *
     * @param key
     * @param value
     */
    public Object put(String key, Object value) {
        return atte.put(key, value);
    }

    /**
     * 获取附件
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return atte.get(key);
    }

}
