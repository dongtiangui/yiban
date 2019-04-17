package com.yiban.yiban_application.util;

import java.util.List;

public class ResponseResult<T> {

    private Integer status;
    private String message;
    private String error;

    private List<T> result;

    public ResponseResult() {}

    public ResponseResult(Integer status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", result=" + result +
                '}';
    }
}
