package com.example.aop;

import com.example.dao.StudentDao;
import com.example.dao.impl.StudentDaoImpl;
import com.example.entity.Student;
import com.example.spring.BeanFactory;
import com.example.spring.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) throws Exception {
        BeanFactory  beanFactory=new ClassPathXmlApplicationContext();
        StudentDao rent =(StudentDaoImpl)beanFactory.getBean("u");
        rent.addStudent(new Student());
        JdkProxy jdkProxy = new JdkProxy(rent);
        StudentDao rentProxy = (StudentDao) Proxy.newProxyInstance(rent.getClass().getClassLoader(), rent.getClass().getInterfaces(), jdkProxy);
        rentProxy.addStudent(new Student());
    }
}
