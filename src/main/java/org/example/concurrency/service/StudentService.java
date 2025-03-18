package org.example.concurrency.service;

import org.example.concurrency.dao.Student;
import org.example.concurrency.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // 查询所有学生
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // 查询指定学生
    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    // 根据姓名查询学生
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    // 根据年龄查询学生
    public List<Student> getStudentsByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    public Student saveStudents(Student student) {
        return studentRepository.save(student);
    }
}
