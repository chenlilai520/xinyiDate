package com.example.spring;

import org.jdom.JDOMException;

import java.io.IOException;

public interface BeanFactory {


    public Object getBean(String name) throws JDOMException, IOException, Exception;
}
