package com.example.demo.obj.msg;

import lombok.Getter;

@Getter
public enum HttpStatus {
    SUCCESS(200, "成功"),
    ERROR(201, "失败"),

    REG_ACCOUNT_FORM_ERROR(1001, "账号格式错误,字母、数字、特殊符号组成，账号字数限制5-20个字符 特殊符号仅限 @ $ ^ ! ~ , . *"),
    REG_PASSWORD_FORM_ERROR(1002, "密码格式错误,字母、数字、特殊符号组成，账号字数限制8-16个字符 特殊符号仅限 @ $ ^ ! ~ , . *"),
    REG_DUPLICATE_ACCOUNT_ERROR(1003, "账号重名了,请换个账号名"),
    LOGIN_FIND_ACCOUNT_ERROR(1004, "账号不存在"),
    LOGIN_PASSWORD_ERROR(1005, "密码错误"),

    UNKNOWN_ERROR(9999, "未知错误");

    private Integer code;
    private String msg;

    HttpStatus(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}