package com.ishow.ssm.model;


import io.swagger.annotations.ApiModel;

/**
 * Created by yuhaiyang on 2017/6/20.
 * 请求的返回
 */
@ApiModel(value="Result", discriminator = "Result", subTypes = {Result.class})
public class Result {
    /**
     * 返回成功
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * 返回失败
     */
    public static final int CODE_FAILED = 1;
    /**
     * 返回的状态码
     */
    private int code;
    /**
     * 返回状态的内容
     */
    private String message;
    /**
     * 返回的信息
     */
    private Object value;


    public Result(int code, Object value) {
        this(code, null, value);
    }

    @SuppressWarnings("WeakerAccess")
    public Result(int code, String message, Object value) {
        this.code = code;
        this.message = message;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
