package com.legendmohe.rappid.helper;

import com.legendmohe.rappid.util.ByteUtil;

/**
 * 读取Buff工具类
 *
 * @author Liuxy
 * @2015年1月14日上午11:51:09 </br>
 * @explain
 */
public class BufferReader {
    // 当前读取数据的索引
    private int index;
    // 数据
    private byte[] buf;

    public BufferReader(byte[] bys, int offset) {
        buf = bys;
        this.index = offset;
    }

    /**
     * 设置偏移量,因为偏移量会自己自增，所以不推荐使用,除了特殊情况外。
     *
     * @param offset
     * @deprecated
     */
    public void setIndex(int offset) {
        this.index = offset;
    }

    /**
     * 读取结束
     */
    public void finsh() {
        buf = null;
        index = 0;
    }

    /**
     * 返回所有的byte[]
     *
     * @return
     */
    public byte[] array() {
        return buf;
    }

    /**
     * 每次读取，需要把当前索引值加起来
     *
     * @param count
     */
    private void addIndex(int count) {
        index += count;
        if (buf.length == index) {
        }
    }

    public int getOffset() {
        return index;
    }

    /**
     * 读取一个字节布尔
     *
     * @return
     */
    public boolean readBoolean() {
        byte b = buf[index];
        addIndex(1);
        // 不等于0就是true，等于0为false
        return b != 0;
    }

    @Override
    public String toString() {
        return "当前索引: " + index + " 数据总长度 ：" + buf.length + " " + buf;
    }

    /**
     * 读取一个byte
     *
     * @return
     */
    public byte readByte() {
        byte b = buf[index];
        addIndex(1);
        return b;
    }

    /**
     * 读取剩下的byte[]
     *
     * @return
     */
    public byte[] readEndByte() {
        byte[] bs = new byte[buf.length - index];
        System.arraycopy(buf, index, bs, 0, bs.length);
        index += bs.length;
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
        System.arraycopy(buf, index, b, 0, b.length);
        addIndex(b.length);
        return ByteUtil.byteToInt(b);
    }

    /**
     * 读取2个字节的Short
     *
     * @return
     */
    public short readShort() {
        short s = ByteUtil.byteToShort(buf, index);
        addIndex(2);
        return s;
    }


    /**
     * 读取指定大小的byte[]
     *
     * @return
     */
    public byte[] readBytes(int size) {
        byte[] bs = new byte[size];
        System.arraycopy(buf, index, bs, 0, bs.length);
        addIndex(size);
        return bs;
    }

    public boolean reachEnd() {
        return index >= buf.length;
    }
}