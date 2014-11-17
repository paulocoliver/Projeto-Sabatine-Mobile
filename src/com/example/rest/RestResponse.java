package com.example.rest;

import org.json.JSONObject;

/**
 * Created by paulo on 15/11/14.
 */
public class RestResponse {

    private int statusCode = 0;
    private boolean success = false;
    private String content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
