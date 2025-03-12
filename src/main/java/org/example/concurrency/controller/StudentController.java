package org.example.concurrency.controller;

import org.example.concurrency.dao.Student;
import org.example.concurrency.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void StudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/q")
    public String getString(){
        return "@GetMapping\n" +
                "    public List<Student> getStudentList(){\n" +
                "        return studentService.getAllStudents();\n" +
                "    }";
    }

    @GetMapping("/w")
    public List<Student> getStudentList(){
        return studentService.getAllStudents();
    }
}
