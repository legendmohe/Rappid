package com.legendmohe.rappid.mvp;

/**
 * Created by legendmohe on 16/4/11.
 */
public class MvpBaseEvent {

    private Object data;
    private Enum flag;

    public MvpBaseEvent() {

    }

    public MvpBaseEvent(Enum flag) {
        setFlag(flag);
    }

    public MvpBaseEvent(Enum flag, Object data) {
        setFlag(flag);
        setData(data);
    }

    @Override
    public String toString() {
        return "flag: " + flag + " data: " + data;
    }

    public Enum getFlag() {
        return flag;
    }

    public void setFlag(Enum flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
