package com.example.aop;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CjLibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object intercept = methodProxy.invokeSuper(o, objects);
        System.out.println("中介：该房源已发布！");
        System.out.println("");
        return intercept;
    }
}
