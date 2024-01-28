package com.lilu.designpattern.chainofresponsibility.version1;

public class MiddlewareProcessorAbc implements MiddlewareIf {
    @Override
    public void process(Request request, Response response, MiddlewareChain middlewareChain) {
        request.message = "ABC--" + request.getMessage();
    }
}
