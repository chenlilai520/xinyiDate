package com.example.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxy implements InvocationHandler {

    private Object post;

    JdkProxy(Object post){
        this.post = post;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object invoke = method.invoke(post, args);
        System.out.println("中介：该房源已发布！");
        return invoke;
    }
}
