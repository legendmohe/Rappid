package com.legendmohe.rappid.helper;

import com.legendmohe.rappid.util.ByteUtil;

/**
 * 写数据
 *
 * @author Liuxy
 * @2015年1月14日上午11:51:58 </br>
 * @explain
 */
public class BufferWritter {
    // 当前的数据
    private byte[] buf;
    // 当前写入的索引
    private int index;

    /**
     * @param size 需要写入多大数据
     */
    public BufferWritter(int size) {
        buf = new byte[size];
        index = 0;
    }

    public void addSize(int size) {
        byte[] addbyte = new byte[buf.length + size];
        System.arraycopy(buf, 0, addbyte, 0, buf.length);
        buf = addbyte;
    }


    public int size() {
        return buf.length;
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
        byte[] bytes = new byte[buf.length];
        System.arraycopy(buf, 0, bytes, 0, buf.length);
        return bytes;
    }

    public byte getIndex(int i) {
        return buf[i];
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
        System.arraycopy(data, 0, buf, index, len);
        index += len;
    }

    public void writeBytes(BufferWritter data) {
        int len = data.size();
        System.arraycopy(data.buf, 0, buf, index, len);
        index += len;
    }

    /**
     * 返回当前写的索引值
     *
     * @return
     */
    public int getIndex() {
        return index;

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
        buf[index] = b;
        index++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (int i = 0; i < buf.length; i++) {
            builder.append(ByteUtil.byteToHex(buf[i]));
            builder.append(" ");
        }
        builder.append(" ]");
        return builder.toString();
    }
}
