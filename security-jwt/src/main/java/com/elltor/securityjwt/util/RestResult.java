package com.elltor.securityjwt.util;

import java.util.HashMap;
import java.util.Map;

/**
 * api 接口返回结果封装
 *
 * @author lqc
 */
public class RestResult {
    private String msg;
    private int code;
    private Object data;

    public RestResult() {

    }

    public RestResult(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static RestResult newInstance() {
        return new RestResult();
    }

    public RestResult put(String key, Object data) {
        if (this.data == null) {
            this.data = new HashMap<String, Object>();
        }
        ((Map) this.data).put(key, data);
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
