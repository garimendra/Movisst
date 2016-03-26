package com.example.alphacoder.movisst.Model;

/**
 * Created by alphacoder on 26/03/16.
 */
public class AccessToken {
    String request_token;
    String status_message;
    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }
}
