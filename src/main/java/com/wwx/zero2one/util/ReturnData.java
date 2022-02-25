package com.wwx.zero2one.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private Object data;
    private String message;

    public ReturnData(){

    }

    public ReturnData(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static ReturnData ok(){
        return new ReturnData(200, null, "success");
    }

    public static ReturnData ok(Object data, String message) {
        return new ReturnData(200, data, message);
    }

    public static ReturnData fail(Integer code, Object data, String message) {
        return new ReturnData(code, data, message);
    }
}
