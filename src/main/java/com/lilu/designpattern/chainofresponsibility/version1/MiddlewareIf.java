package com.lilu.designpattern.chainofresponsibility.version1;

public interface MiddlewareIf {
    void process(Request request, Response response, MiddlewareChain middlewareChain);
}
