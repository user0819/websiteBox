package com.tool.box.dto;

import lombok.Data;

@Data
public class HttpResponse<T> {
    private int code;
    private T data;
    private String message;

    public HttpResponse(int code, T data, String message)
    {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public HttpResponse(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public static <T> HttpResponse<T> success(T data)
    {
        return new HttpResponse<>(0, data, "success");
    }

    public static <T> HttpResponse<T> fail(String message) {
        return new HttpResponse<>(-1, null, message);
    }

    public static <T> HttpResponse<T> fail(int code, String message) {
        return new HttpResponse<>(code, null, message);
    }
}
