package com.example.service;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentService {

    //private StudentDao studentDAO = new StudentDaoImpl();
    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public boolean addStudent(Student student) {
        System.out.println(studentDao);
        return this.studentDao.addStudent(student);

    }

    public String queryStudent(String studentName) {

        Student retStudent = this.studentDao.queryStudent(studentName);
        if (null == retStudent)
            return "null";
        else
            return retStudent.toString();

    }


    public static void main(String[] args) {

        // create and publish an endpoint
        StudentService studentWs = new StudentService();

        Student student1 = new Student();
        student1.setName("Tom");
        student1.setSex("男");
        student1.setBirth("1970/05/08");

        Student student2 = new Student();
        student2.setName("Lili");
        student2.setSex("女");
        student2.setBirth("1972/05/23");

        studentWs.addStudent(student1);
        studentWs.addStudent(student2);

        System.out.println(studentWs.queryStudent("Tom"));
        System.out.println(studentWs.queryStudent("LiLi"));

    }
}
