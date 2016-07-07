package com.legendmohe.rappid.helper;

import com.legendmohe.rappid.util.ByteUtil;

/**
 * 写数据
 *
 * @author Liuxy
 * @2015年1月14日上午11:51:58 </br>
 * @explain
 */
public class BufferWriter {

    private byte[] mBuf;
    private int mIndex;

    /**
     * @param size 需要写入多大数据
     */
    public BufferWriter(int size) {
        mBuf = new byte[size];
        mIndex = 0;
    }

    public void addSize(int size) {
        byte[] addbyte = new byte[mBuf.length + size];
        System.arraycopy(mBuf, 0, addbyte, 0, mBuf.length);
        mBuf = addbyte;
    }


    public int size() {
        return mBuf.length;
    }

    /**
     * 写2个字节short
     *
     * @param srt
     */
    public void writeShort(short srt) {
        byte[] srtbyte = ByteUtil.shortToByte(srt);
        writeBytes(srtbyte);
    }

    /**
     * 写2个字节short
     *
     * @param srt
     */
    public void writeShort(int srt) {
        writeShort((short) srt);
    }

    /**
     * 获取byte array
     *
     * @return
     */
    public byte[] toBytes() {
        byte[] bytes = new byte[mBuf.length];
        System.arraycopy(mBuf, 0, bytes, 0, mBuf.length);
        return bytes;
    }

    public byte byteAtIndex(int i) {
        return mBuf[i];
    }

    /**
     * 写4个字节int
     *
     * @param i
     */
    public void writeInt(int i) {
        byte[] srtbyte = ByteUtil.intToByte(i);
        writeBytes(srtbyte);
    }

    /**
     * 写入byte[]
     *
     * @param data
     */
    public void writeBytes(byte[] data) {
        int len = data.length;
        System.arraycopy(data, 0, mBuf, mIndex, len);
        mIndex += len;
    }

    public void writeBytes(BufferWriter data) {
        int len = data.size();
        System.arraycopy(data.mBuf, 0, mBuf, mIndex, len);
        mIndex += len;
    }

    /**
     * 返回当前写的索引值
     *
     * @return
     */
    public int getIndex() {
        return mIndex;
    }

    /**
     * 写入1个字节的布尔
     *
     * @param b
     */
    public void writeBoolean(boolean b) {
        byte by = 0;
        if (b) {
            by = 0x01;
        }
        writeByte(by);
    }

    /**
     * 写入1个字节的byte
     *
     * @param b
     */
    public void writeByte(int b) {
        writeByte((byte) b);
    }

    public void writeByte(byte b) {
        mBuf[mIndex] = b;
        mIndex++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (int i = 0; i < mBuf.length; i++) {
            builder.append(ByteUtil.byteToHex(mBuf[i]));
            builder.append(" ");
        }
        builder.append(" ]");
        return builder.toString();
    }

    public boolean reachEnd() {
        return mIndex >= mBuf.length;
    }

    public int numberOfSlot() {
        return mBuf.length - mIndex;
    }
}
