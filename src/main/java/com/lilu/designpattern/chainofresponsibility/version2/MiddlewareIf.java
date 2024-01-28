package com.lilu.designpattern.chainofresponsibility.version2;


public interface MiddlewareIf {
    void process(Request request, Response response, MiddlewareChain middlewareChain);
}
