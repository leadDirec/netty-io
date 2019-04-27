package com.leadDirec.net.exchange;

import com.leadDirec.net.io.buffer.TByteBuffer;

import java.io.IOException;
import java.io.ObjectInput;

public class ObjectInputAdapter implements ObjectInput{

    /**
     * channelByteBuffer
     */
    private TByteBuffer tByteBuffer;

    /**
     *
     */
    public ObjectInputAdapter() {
    }

    /**
     *
     */
    public ObjectInputAdapter(TByteBuffer tByteBuffer) {
        this.tByteBuffer = tByteBuffer;
    }

    /**
     * 重置
     *
     * @param tByteBuffer
     * @return
     */
    ObjectInputAdapter resetChannelByteBuffer(TByteBuffer tByteBuffer) {
        this.tByteBuffer = tByteBuffer;
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readFully(byte[])
     */
    public void readFully(byte[] b) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readFully(byte[], int, int)
     */
    public void readFully(byte[] b, int off, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#skipBytes(int)
     */
    public int skipBytes(int n) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readBoolean()
     */
    public boolean readBoolean() throws IOException {
        return tByteBuffer.readBoolean();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readByte()
     */
    public byte readByte() throws IOException {
        return tByteBuffer.readByte();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readUnsignedByte()
     */
    public int readUnsignedByte() throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readShort()
     */
    public short readShort() throws IOException {
        return tByteBuffer.readShort();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readUnsignedShort()
     */
    public int readUnsignedShort() throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readChar()
     */
    public char readChar() throws IOException {
        return tByteBuffer.readChar();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readInt()
     */
    public int readInt() throws IOException {
        return tByteBuffer.readInt();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readLong()
     */
    public long readLong() throws IOException {
        return tByteBuffer.readLong();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readFloat()
     */
    public float readFloat() throws IOException {
        return tByteBuffer.readFloat();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readDouble()
     */
    public double readDouble() throws IOException {
        return tByteBuffer.readDouble();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readLine()
     */
    public String readLine() throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.DataInput#readUTF()
     */
    public String readUTF() throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectInput#readObject()
     */
    public Object readObject() throws ClassNotFoundException, IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectInput#read()
     */
    public int read() throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectInput#read(byte[])
     */
    public int read(byte[] b) throws IOException {
        tByteBuffer.readBytes(b);
        return b.length;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectInput#read(byte[], int, int)
     */
    public int read(byte[] b, int off, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectInput#skip(long)
     */
    public long skip(long n) throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectInput#available()
     */
    public int available() throws IOException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.io.ObjectInput#close()
     */
    public void close() throws IOException {
        // throw new UnsupportedOperationException();
    }

}

