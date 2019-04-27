package com.study.dispatcher.action;

import com.study.dispatcher.action.impl.DefaultActionProxy;
import com.study.dispatcher.action.impl.DefaultActionProxyFactory;
import com.study.dispatcher.action.mapper.ActionMapping;
import com.study.dispatcher.factory.IBeanFactory;
import com.study.dispatcher.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xiangdao
 */
public class ActionProxyFactoryBuilder {

    private ActionProxyFactory actionProxyFactory;

    public ActionProxyFactoryBuilder(List<String> packageNames, IBeanFactory beanFactory) throws Exception{
        Map<Integer, ActionProxy> actionProxyMap = new HashMap<Integer, ActionProxy>();
        for (String packageName : packageNames) {
            Set<Class<?>> classes = ClassUtils.getClasses(packageName);
            for (Class<?> clazz : classes) {
                for (Method method : clazz.getMethods()) {
                    ActionMapping actionMapping = method.getAnnotation(ActionMapping.class);
                    if(actionMapping != null) {
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes == null
                                || parameterTypes.length != 2
                                || !parameterTypes[0].getName().equals("com.jjjr.tt.net.transport.TChannel")
                                || (!parameterTypes[1].getName().equals("com.jjjr.tt.common.dto.MessageBean") && !parameterTypes[1]
                                .getSuperclass().getName().equals("com.jjjr.tt.common.dto.AbstractMessageBean"))) {
                            throw new IllegalArgumentException(
                                    "ActionMapping方法参数只能是[0]=TChannel.class,[1]=MessageBean.class");
                        }
                        Object action = beanFactory.getBean(clazz);
                        if (action != null) {
                            ActionProxy existActionProxy = actionProxyMap.get(actionMapping.protocolId());
                            if (existActionProxy != null) {
                                throw new IllegalArgumentException(action.getClass().getName() + "方法"
                                        + method.getName() + "中的ActionMapping protocolId:"
                                        + String.format("0x%08x", actionMapping.protocolId()) + "已经存在");
                            }
                            actionProxyMap.put(actionMapping.protocolId(),
                                    new DefaultActionProxy(actionMapping.protocolId(), action, method));
                        }
                    }
                }
            }
        }
        actionProxyFactory = new DefaultActionProxyFactory(actionProxyMap);
    }

    public ActionProxyFactory build() {
        return actionProxyFactory;
    }

}