package com.ishow.ssm.util.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ishow.ssm.model.Result;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.ui.Model;

/**
 * Created by yuhaiyang on 2017/6/20.
 * REST 管理
 * 封装的接口
 */
public class RESTManager {


    public static Result sendFailedResponse() {
        return sendFailedResponse(Result.CODE_FAILED, "failed", "null");
    }

    public static Result sendFailedResponse(Object value) {
        return sendFailedResponse(Result.CODE_FAILED, "failed", value);
    }

    public static Result sendFailedResponse(int code, Object value) {
        return sendFailedResponse(code, "failed", value);
    }

    public static Result sendFailedResponse(String message, Object value) {
        return sendFailedResponse(Result.CODE_FAILED, message, value);
    }

    @SuppressWarnings("WeakerAccess")
    public static Result sendFailedResponse(int code, String message, Object value) {
        return new Result(code, message, value);
    }


    public static Result sendSuccessResponse(Object value) {
        return sendSuccessResponse(Result.CODE_SUCCESS, "success", value);
    }

    public static Result sendSuccessResponse(int code, Object value) {
        return sendSuccessResponse(code, "success", value);
    }

    public static Result sendSuccessResponse(String message, Object value) {
        return sendSuccessResponse(Result.CODE_SUCCESS, message, value);
    }

    @SuppressWarnings("WeakerAccess")
    public static Result sendSuccessResponse(int code, String message, Object value) {
        return new Result(code, message, value);
    }
}
