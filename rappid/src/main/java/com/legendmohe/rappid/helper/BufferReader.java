package com.legendmohe.rappid.helper;

import com.legendmohe.rappid.util.ByteUtil;

import java.util.Arrays;

/**
 * 读取Buff工具类
 *
 * @author Liuxy
 * @2015年1月14日上午11:51:09 </br>
 * @explain
 */
public class BufferReader {

    private int mIndex;
    private byte[] mBuf;

    public BufferReader(byte[] bys) {
        mBuf = bys;
        this.mIndex = 0;
    }

    public BufferReader(byte[] bys, int offset) {
        mBuf = bys;
        this.mIndex = offset;
    }

    public void setIndex(int offset) {
        this.mIndex = offset;
    }

    /**
     * 读取结束
     */
    public void finsh() {
        mBuf = null;
        mIndex = 0;
    }

    /**
     * 返回所有的byte[]
     *
     * @return
     */
    public byte[] array() {
        return mBuf;
    }

    /**
     * 每次读取，需要把当前索引值加起来
     *
     * @param count
     */
    private void addIndex(int count) {
        mIndex += count;
    }

    public int getOffset() {
        return mIndex;
    }

    /**
     * 读取一个字节布尔
     *
     * @return
     */
    public boolean readBoolean() {
        byte b = mBuf[mIndex];
        addIndex(1);
        // 不等于0就是true，等于0为false
        return b != 0;
    }

    public byte readByte() {
        byte b = mBuf[mIndex];
        addIndex(1);
        return b;
    }

    public byte peek() {
        return mBuf[mIndex];
    }

    public int size() {
        return mBuf.length;
    }

    public int unread() {
        return mBuf.length - mIndex;
    }

    /**
     * 读取剩下的byte[]
     *
     * @return
     */
    public byte[] readRest() {
        byte[] bs = new byte[mBuf.length - mIndex];
        System.arraycopy(mBuf, mIndex, bs, 0, bs.length);
        mIndex += bs.length;
        finsh();
        return bs;
    }

    /**
     * 读取4个字节的int
     *
     * @return
     */
    public int readInt() {
        byte[] b = new byte[4];
        System.arraycopy(mBuf, mIndex, b, 0, b.length);
        addIndex(b.length);
        return ByteUtil.byteToInt(b);
    }

    /**
     * 读取2个字节的Short
     *
     * @return
     */
    public short readShort() {
        short s = ByteUtil.byteToShort(mBuf, mIndex);
        addIndex(2);
        return s;
    }

    public byte[] readBytes(int size) {
        byte[] bs = peekBytes(size);
        addIndex(size);
        return bs;
    }

    public byte[] peekBytes(int size) {
        byte[] bs = new byte[size];
        int len = Math.min(mBuf.length - mIndex, size);
        System.arraycopy(mBuf, mIndex, bs, 0, len);
        return bs;
    }

    public boolean hasNext() {
        return unread() > 0;
    }

    @Override
    public String toString() {
        return "BufferReader{" +
                "mIndex=" + mIndex +
                ", mBuf=" + Arrays.toString(mBuf) +
                '}';
    }
}
