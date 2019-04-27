package com.study.gate.action;

import com.study.dispatcher.action.mapper.ActionMapping;
import com.study.gate.service.TestService;
import com.study.net.transport.TChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 心跳请求Action
 * 
 * @author xiangdao
 *
 */
@Controller
public class TestAction {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestAction.class);

    @Resource
    private TestService testService;

    @ActionMapping(uri = "/test")
    public void heartbeat(TChannel channel, Object data) {

    }

}
