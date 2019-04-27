package com.leadDirec.gate.net.codec;

import com.leadDirec.common.dto.MessageBean;

import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * 编码解码辅助工具
 *
 * @author xiangdao
 *
 */
public final class ImProtocolCodecUtils {

    /**
     * 构造方法
     */
    private ImProtocolCodecUtils() {

    }

    /**
     * 编码
     *
     * @param message
     * @param objectOutput
     * @throws Exception
     */
    public static void encode(Object message, ObjectOutput objectOutput) throws Exception {
        if (!(message instanceof MessageBean)) {
            throw new IllegalArgumentException("编码器编码对象message必须实现MessageBean");
        }
        MessageBean messageBean = (MessageBean) message;
        objectOutput.writeInt(messageBean.getProtocolId());
        objectOutput.writeShort(0);
        messageBean.writeExternal(objectOutput);
    }

    /**
     * 解码
     *
     * @param objectInput
     * @return MessageBean
     * @throws Exception
     */
    public static MessageBean decode(ObjectInput objectInput) throws Exception {
        int protocolId = objectInput.readInt();
        short version = objectInput.readShort();
        MessageBean messageBean = ProtocolMessageBeanFactory.getInstance().getMessageBean(protocolId);
        messageBean.readExternal(objectInput);
        return messageBean;
    }

}
