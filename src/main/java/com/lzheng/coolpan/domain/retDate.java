package com.lzheng.coolpan.domain;



/**
 * @ClassName retDate
 * @Author 刘正
 * @Date 2019/12/15 12:32
 * @Version 1.0
 * @Description:
 */


public class retDate {
    private String code;
    private String msg;
    private int count;
    private Object[] data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}
