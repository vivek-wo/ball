package com.vivek.wo.ball.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    private int code;
    private String msg;
    private T data;

    public ResultVo(int code, T t) {
        this(code, null, t);
    }

    public ResultVo(int code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.data = t;
    }
}
