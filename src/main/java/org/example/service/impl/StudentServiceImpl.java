package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.example.service.StudentService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @DS("master")
    public Student queryStudentMaster(Integer id) {
        return studentMapper.selectOne(id);
    }

    @Override
    @DS("slave")
    public Student queryStudentSlave(Integer id) {
        return studentMapper.selectOne(id);
    }

    @Override
    @DS("master")
    public int addStudentMaster(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    @DS("slave")
    public int addStudentSlave(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    @DSTransactional
    public void addStudentAll(Student student) {
        StudentService studentService = (StudentService) AopContext.currentProxy();

        int i1 = studentService.addStudentMaster(student);
        // int i = 1 / 0;
        int i2 = studentService.addStudentSlave(student);
    }

}
