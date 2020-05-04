package com.bishe.utils;

import com.alibaba.fastjson.JSONObject;

public class Result {
    private String code;
    private String msg;
    private Object data;

    public static final String success = "200";
    public static final String error = "400";

    public Result(){}

    public Result(String code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JSONObject data(String code, String msg, Object data) {
        JSONObject result = new JSONObject();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

    public static JSONObject data(Boolean bool) {
        JSONObject result = new JSONObject();
        if (bool) {
            result.put("code", Result.success);
            result.put("msg", "success");
        } else {
            result.put("code", Result.error);
            result.put("msg", "false");
        }
        result.put("data", bool);
        return result;
    }
}
