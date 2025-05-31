package org.example.controller;

import org.apache.ibatis.annotations.Param;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
