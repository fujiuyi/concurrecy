package org.example.concurrency.repository;

import org.example.concurrency.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // 根据姓名查询学生
    List<Student> findByName(String name);

    // 根据年龄查询学生
    List<Student> findByAge(Integer age);
}
