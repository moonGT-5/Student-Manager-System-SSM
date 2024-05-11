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

    public Student findStudentByName(String name) {
        return studentMapper.findStudentByName(name);
    }

    public boolean deleteStudentById(int id) {
        int affectedRows = studentMapper.deleteStudentById(id);
        return affectedRows > 0;
    }

    public boolean addStudent(Student student) {
        int affectedRows = studentMapper.addStudent(student);
        return affectedRows > 0;
    }
}