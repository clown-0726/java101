package com.lilu.designpattern.chainofresponsibility.version1;

public class MiddlewareProcessorDef implements MiddlewareIf {
    @Override
    public void process(Request request, Response response, MiddlewareChain middlewareChain) {
        request.message = "DEF--" + request.getMessage();
    }
}
