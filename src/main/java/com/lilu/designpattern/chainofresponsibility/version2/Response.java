package com.lilu.designpattern.chainofresponsibility.version2;

public class Response {

    public Response(String message) {
        this.message = message;
    }

    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
