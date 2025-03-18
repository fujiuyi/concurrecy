package org.example.concurrency.controller;

import jakarta.websocket.server.PathParam;
import org.example.concurrency.dao.Student;
import org.example.concurrency.service.RedisService;
import org.example.concurrency.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    private RedisService redisService;

    @Autowired
    public void StudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping
    public List<Student> getStudentList() {
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return redisService.getStudent(id);
    }

    @PostMapping
    public Student setStudentList() {
        Student student = new Student();
        student.setName("小明");
        student.setAge(12);
        student = studentService.saveStudents(student);
        redisService.setStudent(student);
        return student;
    }
}
