package org.example.service;

import org.example.entity.Student;

public interface StudentService {

    Student queryStudentMaster(Integer id);

    Student queryStudentSlave(Integer id);

    int addStudentMaster(Student student);

    int addStudentSlave(Student student);

    void addStudentAll(Student student);

}
