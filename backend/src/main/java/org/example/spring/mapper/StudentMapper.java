package org.example.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.spring.entity.Student;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> findAllStudents();

    Student findStudentById(int id);

    Student findStudentByName(String name);

    int deleteStudentById(int id);

    void insertStudent(Student student);
    void updateStudentById(Student student);
}

