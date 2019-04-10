package com.example.dao.impl;

import com.example.dao.StudentDao;
import com.example.entity.Student;

public class StudentDaoImpl implements StudentDao {

    public StudentDaoImpl() {
    }

    @Override
    public boolean addStudent(Student student) {
        // TODO Auto-generated method stub
        System.out.println("++++begin add student+++++");
        //模拟add 操作
        int flag =1;
        if(flag ==0)
            return false;
        else
            return true;

    }

    @Override
    public boolean delStudent(Student student) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean modifyStudent(Student student) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Student queryStudent(String StudentName) {
        // TODO Auto-generated method stub
        //模拟从数据库中查询学生名, 数据库中只有一名 name 为 Tom的学生
        System.out.println("++++begin query student+++++");

        if("Tom".equals(StudentName ))
        {
            Student studentTom = new Student();
            studentTom.setName("Tom");
            studentTom.setSex("male");
            studentTom.setBirth("19701221");
            return  studentTom;
        }
        else {
            return null;
        }
    }

}

