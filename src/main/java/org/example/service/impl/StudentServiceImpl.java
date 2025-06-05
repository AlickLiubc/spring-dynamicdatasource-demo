package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import org.example.entity.Student;
import org.example.mapper.StudentMapper;
import org.example.service.StudentService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Override
    public void batchAddStudent(List<Student> itemList) {
        if (itemList == null || itemList.isEmpty()) {
            return;
        }

        int size = itemList.size();
        int nThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++) {
            List<Student> inputItemList = itemList.subList(size / nThreads * i, size / nThreads * (i + 1));
            executorService.execute(() -> {
                try {
                    studentMapper.batchInsertStudent(inputItemList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    @Override
    public void batchAddStudent2(List<Student> itemList) {
        if (itemList == null || itemList.isEmpty()) {
            return;
        }

        int c = 100;
        int b = itemList.size() / c;
        int d = itemList.size() % c;

        for (int e = c; e <= c * b; e = e + c) {
            studentMapper.batchInsertStudent(itemList.subList(e - c, e));
        }

        if (d != 0) {
            studentMapper.batchInsertStudent(itemList.subList(c * b, itemList.size()));
        }
    }

}
