package com.example.demo.obj.msg;

import lombok.Data;

@Data
public class SRet<T>{
    private Integer code;
    private String msg;
    private T data;

    SRet(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static SRet success(){
        return new SRet(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMsg(), null);
    }

    public static SRet success(String msg){
        return new SRet(HttpStatus.SUCCESS.getCode(), msg, null);
    }

    public static <T> SRet<T> success(T data){
        return new SRet(HttpStatus.SUCCESS.getCode(), HttpStatus.SUCCESS.getMsg(), data);
    }


    public static SRet error(){
        return new SRet(HttpStatus.ERROR.getCode(), HttpStatus.ERROR.getMsg(), null);
    }

    public static SRet error(Integer code, String msg){
        return new SRet(code, msg, null);
    }
}