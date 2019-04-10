package com.example.aop;

import com.example.dao.StudentDao;
import com.example.dao.impl.StudentDaoImpl;
import com.example.entity.Student;
import org.springframework.cglib.proxy.Enhancer;

public class CjLibProxyTest {

    public static void main(String[] args) {
        CjLibProxy cjLibProxy=new CjLibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(StudentDaoImpl.class);
        enhancer.setCallback(cjLibProxy);
        StudentDao rentProxy = (StudentDao)enhancer.create();
        rentProxy.addStudent(new Student());
    }
}
