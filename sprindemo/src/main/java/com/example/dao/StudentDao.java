package com.example.dao;

import com.example.entity.Student;

public interface StudentDao {

    //学生操作，新增学生
    boolean addStudent(Student student);

    //学生操作，删除学生
    boolean delStudent(Student  student);

    //学生操作，修改学生信息
    boolean modifyStudent(Student  student);

    //学生操作，查询学生信息，查询到返回学生对象，否则返回null
    Student queryStudent( String  StudentName);

}
