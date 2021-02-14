package com.bs.bs_system.common;

import java.io.Serializable;

/**
 * 请求结果
 *
 * @param <T>
 */
public class RequesResult<T> implements Serializable {
    private static final long serialVersionUID = 8272900994954367443L;

    private String code;    //0 成功  1 失败
    private String msg;     //返回结果信息
    private Object data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
