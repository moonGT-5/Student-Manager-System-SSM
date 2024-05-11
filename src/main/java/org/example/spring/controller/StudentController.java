package org.example.spring.controller;

import org.example.spring.entity.Student;
import org.example.spring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/{id:[0-9]+}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/students/{name:[A-Za-z]+|[\\u4e00-\\u9fa5]+}")
    public ResponseEntity<Student> searchStudentByName(@PathVariable String name) {
        Student student = studentService.findStudentByName(name);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/students/{id:[0-9]+}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable int id) {
        boolean deleted = studentService.deleteStudentById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/students/submit")
    public ResponseEntity<?> submitForm(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}