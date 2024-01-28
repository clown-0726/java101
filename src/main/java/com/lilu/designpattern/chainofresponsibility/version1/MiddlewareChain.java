package com.lilu.designpattern.chainofresponsibility.version1;

import java.util.ArrayList;
import java.util.List;

public class MiddlewareChain {
    public List<MiddlewareIf> middlewareList = new ArrayList<>();

    public MiddlewareChain addMiddleware(MiddlewareIf middleware) {
        middlewareList.add(middleware);
        return this;
    }

    public void doProcess(Request request, Response response, MiddlewareChain middlewareChain) {
        for (MiddlewareIf middleware : middlewareList) {
            middleware.process(request, response, middlewareChain);
        }
    }

    // test
    public static void main(String[] args) {
        MiddlewareChain middlewareChain = new MiddlewareChain();
        middlewareChain.addMiddleware(new MiddlewareProcessorAbc()).addMiddleware(new MiddlewareProcessorDef());

        Request request = new Request("REQUEST");
        Response response = new Response("RESPONSE");
        middlewareChain.doProcess(request, response, middlewareChain);
        System.out.println(request.getMessage());
        System.out.println(response.getMessage());
    }
}
