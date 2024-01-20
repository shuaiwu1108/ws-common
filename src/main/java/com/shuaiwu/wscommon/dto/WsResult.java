package com.shuaiwu.wscommon.dto;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通用返回结构
 * 2024-01-20 19:13
 */
public class WsResult extends LinkedHashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;

    public WsResult() {
    }

    public WsResult(int code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public WsResult(Map<String, ?> map) {
        this.setMap(map);
    }

    public Integer getCode() {
        return (Integer)this.get("code");
    }

    public String getMessage() {
        return (String)this.get("message");
    }

    public Object getData() {
        return this.get("data");
    }

    public WsResult setCode(int code) {
        this.put("code", code);
        return this;
    }

    public WsResult setMessage(String message) {
        this.put("message", message);
        return this;
    }

    public WsResult setData(Object data) {
        this.put("data", data);
        return this;
    }

    public WsResult set(String key, Object data) {
        this.put(key, data);
        return this;
    }

    public WsResult setMap(Map<String, ?> map) {
        Iterator var2 = map.keySet().iterator();

        while(var2.hasNext()) {
            String key = (String)var2.next();
            this.put(key, map.get(key));
        }

        return this;
    }

    public static WsResult ok() {
        return new WsResult(200, "ok", (Object)null);
    }

    public static WsResult ok(String message) {
        return new WsResult(200, message, (Object)null);
    }

    public static WsResult code(int code) {
        return new WsResult(code, (String)null, (Object)null);
    }

    public static WsResult data(Object data) {
        return new WsResult(200, "ok", data);
    }

    public static WsResult error() {
        return new WsResult(500, "error", (Object)null);
    }

    public static WsResult error(String message) {
        return new WsResult(500, message, (Object)null);
    }

    public static WsResult get(int code, String message, Object data) {
        return new WsResult(code, message, data);
    }

    public String toString() {
        return "{\"code\": " + this.getCode() + ", \"message\": " + this.transValue(this.getMessage()) + ", \"data\": " + this.transValue(this.getData()) + "}";
    }

    private String transValue(Object value) {
        if (value == null) {
            return null;
        } else {
            return value instanceof String ? "\"" + value + "\"" : String.valueOf(value);
        }
    }
}

