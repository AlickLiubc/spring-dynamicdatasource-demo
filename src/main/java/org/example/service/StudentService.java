package org.example.service;

import org.example.entity.Student;

import java.util.List;

public interface StudentService {

    Student queryStudentMaster(Integer id);

    Student queryStudentSlave(Integer id);

    int addStudentMaster(Student student);

    int addStudentSlave(Student student);

    void addStudentAll(Student student);

    void batchAddStudent(List<Student> itemList);

    void batchAddStudent2(List<Student> itemList);

}
