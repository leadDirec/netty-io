package com.leadDirec.net.io.buffer.netty;

import com.leadDirec.net.io.buffer.TByteBuffer;
import io.netty.buffer.ByteBuf;

/**
 * @author xiangdao
 */
public class NettyByteBuf implements TByteBuffer {

    /**
     * byteBuf
     */
    protected ByteBuf byteBuf;

    /**
     * 空
     */
    private static final byte[] EMPTY_BYTES = new byte[0];

    /**
     * 构造方法
     *
     * @param byteBuf
     */
    public NettyByteBuf(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.tt.net.io.buffer.TByteBuffer#toBytes()
     */
    @Override
    public byte[] toBytes() {
        if (byteBuf.isReadable()) {
            byte[] bytes = new byte[readableBytes()];
            readBytes(bytes);
            return bytes;
        }
        return EMPTY_BYTES;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#capacity()
     */
    public int capacity() {
        return byteBuf.capacity();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.tt.net.io.buffer.TByteBuffer#readableBytes()
     */
    @Override
    public int readableBytes() {
        return byteBuf.readableBytes();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#clear()
     */
    public void clear() {
        byteBuf.clear();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.tt.net.io.buffer.ChannelByteBuffer#release()
     */
    public void release() {
        byteBuf.release();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#isDirect()
     */
    public boolean isDirect() {
        return byteBuf.isDirect();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.tt.net.io.buffer.TByteBuffer#setBytes(int,
     * com.jjjr.tt.net.io.buffer.TByteBuffer)
     */
    @Override
    public void setBytes(int index, TByteBuffer src) {
        // TODO 要考虑转型异常
        byteBuf.setBytes(index, ((NettyByteBuf) src).byteBuf);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getBoolean(int)
     */
    public boolean getBoolean(int index) {
        return byteBuf.getBoolean(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getByte(int)
     */
    public byte getByte(int index) {
        return byteBuf.getByte(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getShort(int)
     */
    public short getShort(int index) {
        return byteBuf.getShort(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getInt(int)
     */
    public int getInt(int index) {
        return byteBuf.getInt(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getLong(int)
     */
    public long getLong(int index) {
        return byteBuf.getLong(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getChar(int)
     */
    public char getChar(int index) {
        return byteBuf.getChar(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getFloat(int)
     */
    public float getFloat(int index) {
        return byteBuf.getFloat(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#getDouble(int)
     */
    public double getDouble(int index) {
        return byteBuf.getDouble(index);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readBoolean()
     */
    public boolean readBoolean() {
        return byteBuf.readBoolean();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readByte()
     */
    public byte readByte() {
        return byteBuf.readByte();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readShort()
     */
    public short readShort() {
        return byteBuf.readShort();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readInt()
     */
    public int readInt() {
        return byteBuf.readInt();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readLong()
     */
    public long readLong() {
        return byteBuf.readLong();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readChar()
     */
    public char readChar() {
        return byteBuf.readChar();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readFloat()
     */
    public float readFloat() {
        return byteBuf.readFloat();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readDouble()
     */
    public double readDouble() {
        return byteBuf.readDouble();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readBytes(int)
     */
    public byte[] readBytes(int length) {
        byte[] dst = new byte[length];
        byteBuf.readBytes(dst);
        return dst;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#readBytes(byte[])
     */
    public void readBytes(byte[] dst) {
        byteBuf.readBytes(dst);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeBoolean(boolean)
     */
    public void writeBoolean(boolean value) {
        byteBuf.writeBoolean(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeByte(int)
     */
    public void writeByte(int value) {
        byteBuf.writeByte(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeShort(int)
     */
    public void writeShort(int value) {
        byteBuf.writeShort(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeInt(int)
     */
    public void writeInt(int value) {
        byteBuf.writeInt(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeLong(long)
     */
    public void writeLong(long value) {
        byteBuf.writeLong(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeChar(int)
     */
    public void writeChar(int value) {
        byteBuf.writeChar(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeFloat(float)
     */
    public void writeFloat(float value) {
        byteBuf.writeFloat(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeDouble(double)
     */
    public void writeDouble(double value) {
        byteBuf.writeDouble(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.jjjr.common.net.io.buffer.ChannelByteBuffer#writeBytes(byte[])
     */
    public void writeBytes(byte[] src) {
        byteBuf.writeBytes(src);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.jjjr.tt.net.io.buffer.TByteBuffer#writeBytes(com.jjjr.tt.net.io.buffer
     * .TByteBuffer)
     */
    @Override
    public void writeBytes(TByteBuffer tByteBuffer) {
        byteBuf.writeBytes(((NettyByteBuf) tByteBuffer).byteBuf);
    }

}
