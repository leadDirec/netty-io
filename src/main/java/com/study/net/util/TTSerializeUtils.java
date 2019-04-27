package com.study.net.util;
//import java.io.Externalizable;
//import java.io.ObjectInput;
//
//
//public class TTSerializeUtils {
//
//    public byte[] serialize(Externalizable t) throws Exception {
//        TByteBuffer tByteBuffer = TByteBufferAllocator.heapBuffer(128);
//        ObjectInput in = new ObjectInputAdapter(tByteBuffer);
//        t.readExternal(in);
//        return tByteBuffer.toBytes();
//    }
//
//    public Externalizable deserialize(byte[] bytes) throws Exception {
//        
//        return null;
//    }
//}
