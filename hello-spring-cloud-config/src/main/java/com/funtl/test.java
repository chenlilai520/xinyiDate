package com.funtl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class test {

    public static void main(String[] args) {
        ApplicationContext ctx  = new ClassPathXmlApplicationContext("bean.xml");
    }

}
