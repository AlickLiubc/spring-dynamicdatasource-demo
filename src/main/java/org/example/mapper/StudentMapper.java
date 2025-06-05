package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Student;

import java.util.List;

@Mapper
public interface StudentMapper {

    Student selectOne(Integer id);

    int insertStudent(Student student);

    int batchInsertStudent(@Param("itemList") List<Student> itemList);

}
