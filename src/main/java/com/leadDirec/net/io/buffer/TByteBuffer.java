package com.leadDirec.net.io.buffer;

/**
 * @author xiangdao
 */
public interface TByteBuffer {

    /**
     * 转换成bytes
     *
     * @return
     */
    byte[] toBytes();

    /**
     * 容积
     *
     * @return
     */
    int capacity();

    /**
     * 可读长度
     *
     * @return
     */
    int readableBytes();

    /**
     * 清除
     */
    void clear();

    /**
     * 释放
     */
    void release();

    /**
     * 是否直接使用内存而非heap
     *
     * @return
     */
    boolean isDirect();

    /**
     * 设置
     *
     * @param index
     * @param src
     */
    public void setBytes(int index, TByteBuffer src);

    /**
     * 查看指定起始索引位置的一个boolean
     */
    boolean getBoolean(int index);

    /**
     * 查看指定起始索引位置的一个byte
     */
    byte getByte(int index);

    /**
     * 查看指定起始索引位置的一个short
     */
    short getShort(int index);

    /**
     * 查看指定起始索引位置的一个int
     */
    int getInt(int index);

    /**
     * 查看指定起始索引位置的一个long
     */
    long getLong(int index);

    /**
     * 查看指定起始索引位置的一个char
     */
    char getChar(int index);

    /**
     * 查看指定起始索引位置的一个float
     */
    float getFloat(int index);

    /**
     * 查看指定起始索引位置的一个double
     */
    double getDouble(int index);

    /**
     * 读取一个boolean
     *
     * @return boolean
     */
    boolean readBoolean();

    /**
     * 读取一个byte
     *
     * @return byte
     */
    byte readByte();

    /**
     * 读取一个short
     *
     * @return short
     */
    short readShort();

    /**
     * 读取一个int
     *
     * @return int
     */
    int readInt();

    /**
     * 读取一个long
     *
     * @return long
     */
    long readLong();

    /**
     * 读取一个char
     *
     * @return char
     */
    char readChar();

    /**
     * 读取一个float
     *
     * @return float
     */
    float readFloat();

    /**
     * 读取一个double
     *
     * @return double
     */
    double readDouble();

    /**
     * 读取一个指定长度的byte[]
     *
     * @return byte[]
     */
    byte[] readBytes(int length);

    /**
     * 读取一个指定长度的byte[]
     */
    void readBytes(byte[] dst);

    /**
     * 写入一个boolean
     *
     * @param value
     */
    void writeBoolean(boolean value);

    /**
     * 写入一个byte
     *
     * @param value
     */
    void writeByte(int value);

    /**
     * 写入一个short
     *
     * @param value
     */
    void writeShort(int value);

    /**
     * 写入一个int
     *
     * @param value
     */
    void writeInt(int value);

    /**
     * 写入一个long
     *
     * @param value
     */
    void writeLong(long value);

    /**
     * 写入一个char
     *
     * @param value
     */
    void writeChar(int value);

    /**
     * 写入一个flaot
     *
     * @param value
     */
    void writeFloat(float value);

    /**
     * 写入一个double
     *
     * @param value
     */
    void writeDouble(double value);

    /**
     * 写入一个byte[]
     *
     * @param src
     */
    void writeBytes(byte[] src);

    /**
     * 写入一个tByteBuffer
     *
     * @param tByteBuffer
     */
    void writeBytes(TByteBuffer tByteBuffer);

}

