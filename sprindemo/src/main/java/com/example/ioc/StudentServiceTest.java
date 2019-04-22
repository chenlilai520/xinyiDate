package com.example.ioc;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.spring.BeanFactory;
import com.example.spring.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentServiceTest {

    public  static StudentService studentService;
    public  static StudentDao studentDAO;

    //注意这里用beforeClass而不是before，表示全部测试函数调用前，调用一次
    @BeforeClass
    public static void init() throws  Exception{
        System.out.println("开始自动注入");
        BeanFactory factory = new ClassPathXmlApplicationContext();
        studentService= (StudentService) factory.getBean("sss");
        studentDAO= (StudentDao)factory.getBean("u");
        studentService.setStudentDao(studentDAO);
        System.out.println("自动注入完成");
    }


    @Test
    public void addStudentTest() throws Exception{
        Student studentObj = new Student();
        assertTrue(studentService.addStudent(studentObj));
    }



}
