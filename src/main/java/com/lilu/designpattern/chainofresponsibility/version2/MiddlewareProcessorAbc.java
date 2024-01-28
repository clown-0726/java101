package com.lilu.designpattern.chainofresponsibility.version2;


public class MiddlewareProcessorAbc implements MiddlewareIf {
    @Override
    public void process(Request request, Response response, MiddlewareChain middlewareChain) {
        request.message = "ABC--" + request.getMessage();
        middlewareChain.doProcess(request, response, middlewareChain);
        response.message = response.getMessage() + "--ABC";
    }
}
