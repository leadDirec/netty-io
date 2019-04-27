package com.leadDirec.net.exchange;

import com.leadDirec.net.io.buffer.TByteBuffer;

import java.io.IOException;
import java.io.ObjectOutput;

/**
 * @author xiangdao
 */
public class ObjectOutputAdapter implements ObjectOutput {

    /**
     * channelByteBuffer
     */
    private TByteBuffer tByteBuffer;

    /**
     * 构造方法
     */
    public ObjectOutputAdapter() {
    }

    /**
     *
     */
    public ObjectOutputAdapter(TByteBuffer tByteBuffer) {
        this.tByteBuffer = tByteBuffer;
    }

    /**
     * 重置
     *
     * @param tByteBuffer
     * @return
     */
    ObjectOutputAdapter resetChannelByteBuffer(TByteBuffer tByteBuffer) {
        this.tByteBuffer = tByteBuffer;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeBoolean(boolean)
     */
    public void writeBoolean(boolean v) throws IOException {
        tByteBuffer.writeBoolean(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeByte(int)
     */
    public void writeByte(int v) throws IOException {
        tByteBuffer.writeByte(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeShort(int)
     */
    public void writeShort(int v) throws IOException {
        tByteBuffer.writeShort(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeChar(int)
     */
    public void writeChar(int v) throws IOException {
        tByteBuffer.writeChar(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeInt(int)
     */
    public void writeInt(int v) throws IOException {
        tByteBuffer.writeInt(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeLong(long)
     */
    public void writeLong(long v) throws IOException {
        tByteBuffer.writeLong(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeFloat(float)
     */
    public void writeFloat(float v) throws IOException {
        tByteBuffer.writeFloat(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeDouble(double)
     */
    public void writeDouble(double v) throws IOException {
        tByteBuffer.writeDouble(v);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeBytes(java.lang.String)
     */
    public void writeBytes(String s) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeChars(java.lang.String)
     */
    public void writeChars(String s) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataOutput#writeUTF(java.lang.String)
     */
    public void writeUTF(String s) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectOutput#writeObject(java.lang.Object)
     */
    public void writeObject(Object obj) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectOutput#write(int)
     */
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectOutput#write(byte[])
     */
    public void write(byte[] b) throws IOException {
        tByteBuffer.writeBytes(b);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectOutput#write(byte[], int, int)
     */
    public void write(byte[] b, int off, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectOutput#flush()
     */
    public void flush() throws IOException {

    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectOutput#close()
     */
    public void close() throws IOException {

    }
}
