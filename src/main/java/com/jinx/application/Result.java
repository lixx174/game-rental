package com.jinx.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Response model of all request from client.
 *
 * @author Jinx
 **/
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> succeed() {
        return succeed((T) null);
    }

    public static <T> Result<T> succeed(Runnable task) {
        task.run();
        return succeed();
    }

    public static <T> Result<T> succeed(T data) {
        return new Result<>(200, "success", data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }
}
