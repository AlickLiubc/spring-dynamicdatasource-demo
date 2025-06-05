package org.example.controller;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/select_one_master")
    public Student selectOneMaster() {
        Student student = studentService.queryStudentMaster(1);

        return student;
    }

    @RequestMapping("/select_one_slave")
    public Student selectOneSlave() {
        Student student = studentService.queryStudentSlave(1);

        return student;
    }

    @RequestMapping("/add_one_all")
    public String addOneSlave(@Param("name")String name, @Param("age")Integer age) {
        Student addStu = new Student();
        addStu.setName(name);
        addStu.setAge(age);
        System.out.println(addStu);

        studentService.addStudentAll(addStu);

        return "success";
    }

    @RequestMapping("/batch_add")
    public String batchAdd() {
        List<Student> itemList = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            Student addStu = new Student();
            addStu.setName(UUID.randomUUID().toString());
            addStu.setAge(19);
            itemList.add(addStu);
        }
        // System.out.println(itemList);

        long startTimeMillis = System.currentTimeMillis();
        // 程序耗时为：3ms
        studentService.batchAddStudent(itemList);

        // 程序耗时为：1293ms
        // studentService.batchAddStudent2(itemList);
        long endTimeMillis = System.currentTimeMillis();
        long totalTimeMillis = endTimeMillis - startTimeMillis;

        System.out.println("程序耗时为：" + totalTimeMillis + "ms");

        return "success";
    }


}
