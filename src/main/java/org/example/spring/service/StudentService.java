package org.example.spring.service;

import org.example.spring.entity.Student;
import org.example.spring.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> findAllStudents() {
        return studentMapper.findAllStudents();
    }

    public Student findStudentById(int id) {
        return studentMapper.findStudentById(id);

    }
}