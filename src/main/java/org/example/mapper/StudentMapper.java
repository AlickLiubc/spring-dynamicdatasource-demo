package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.Student;

@Mapper
public interface StudentMapper {

    Student selectOne(Integer id);

    int insertStudent(Student student);

}
