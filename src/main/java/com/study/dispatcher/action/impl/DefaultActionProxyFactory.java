package com.study.dispatcher.action.impl;

import com.study.dispatcher.action.ActionProxy;
import com.study.dispatcher.action.ActionProxyFactory;

import java.util.Map;
import net.openhft.koloboke.collect.map.IntObjMap;
import net.openhft.koloboke.collect.map.hash.HashIntObjMaps;

public class DefaultActionProxyFactory implements ActionProxyFactory {

    /**
     * koloboke IntMap
     */
    private IntObjMap<ActionProxy> actionProxyMap;

    public DefaultActionProxyFactory(Map<Integer, ActionProxy> actionProxyMap) {
        this.actionProxyMap = HashIntObjMaps.newMutableMap();
        for (ActionProxy actionProxy : actionProxyMap.values()) {
            this.actionProxyMap.put(actionProxy.getProtocolId(), actionProxy);
        }
    }

    @Override
    public ActionProxy getActionProxy(int protocolId) {
        return actionProxyMap.get(protocolId);
    }
}
