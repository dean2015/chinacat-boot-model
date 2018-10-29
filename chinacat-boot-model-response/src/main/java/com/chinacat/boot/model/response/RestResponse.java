package com.chinacat.boot.model.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

/**
 * @author chinacat
 */
@Data
public class RestResponse<E> {

    public static final int SUCCESS = 200;

    public static final int FAIL = 400;

    private RestResponse() {
    }

    public static <T> RestResponse<T> newInstance() {
        return new RestResponse<>();
    }

    public RestResponse<E> success(String message, E data) {
        code = SUCCESS;
        error = message;
        result = data;
        success = true;
        return this;
    }

    public RestResponse<E> success(E data) {
        return success("", data);
    }

    public RestResponse<E> success(String message) {
        return success(message, null);
    }

    public RestResponse<E> fail(Integer code, String message, E data) {
        this.code = code;
        error = message;
        result = data;
        success = false;
        return this;
    }

    public RestResponse<E> fail(Integer code, String message) {
        return fail(code, message, null);
    }

    public RestResponse<E> fail(String message, E data) {
        return fail(FAIL, message, data);
    }

    public RestResponse<E> fail(String message) {
        return fail(FAIL, message);
    }

    private Integer code;
    private String error;
    private E result;
    private Boolean success;

    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();

    @Override
    public String toString() {
        return GSON.toJson(this);
    }

}
