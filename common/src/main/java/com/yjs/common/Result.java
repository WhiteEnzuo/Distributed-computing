package com.yjs.common;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Result
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:51
 * @Created by Enzuo
 */
@Data
public class Result {

    private Boolean isSuccess;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    public static Result ok() {
        Result ok = new Result().setCode(ResultCode.SUCCESS).setMessage("success").setSuccess(true);
        return ok;
    }


    public static Result error() {
        Result error = new Result().setCode(ResultCode.ERROR).setMessage("failed").setSuccess(false);
        return error;
    }

    public static Result forward() {
        Result forward = new Result().setCode(ResultCode.FORWARD).setMessage("forward").setSuccess(true);
        return forward;
    }

    public static Result flush() {
        Result forward = new Result().setCode(ResultCode.FLUSH).setMessage("flush").setSuccess(true);
        return forward;
    }

    public static Result refuse() {
        return new Result().setCode(ResultCode.REFUSE).setMessage("refuse").setSuccess(false);
    }


    public Result data(String key, Object object) {
        data.put(key, object);
        return this;
    }


    public Boolean getSuccess() {
        return isSuccess;
    }

    public Result setSuccess(Boolean success) {
        isSuccess = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }
    public Object get(String key) {
        if(ObjectUtil.isNull(data)){
            data=new HashMap<>();
            return null;
        }
        return data.get(key);
    }

    public Result setData(Map<String, Object> data) {
        this.data = new HashMap<>(data);
        return this;
    }

    public boolean containsKey(String key){
        if(ObjectUtil.isNull(data)){
            data=new HashMap<>();
            return false;
        }
        return data.containsKey(key);
    }

    @Override
    public String toString() {
        return "Result{" +
                "isSuccess=" + isSuccess +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
