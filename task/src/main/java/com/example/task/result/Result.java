package com.example.task.result;

import lombok.Data;


@Data
public class Result<T>  {

    private String code;
    private String message;
    private Object data;

    public Result(Object data) {
        this.data = data;
    }

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result() {

    }
}
