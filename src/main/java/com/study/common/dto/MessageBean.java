package com.study.common.dto;

import java.io.Externalizable;

public interface MessageBean extends Externalizable {
    /**
     * 获取协议Id
     *
     * @return protocolId
     */
    int getProtocolId();

}
