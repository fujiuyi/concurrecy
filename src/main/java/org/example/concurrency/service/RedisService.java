package org.example.concurrency.service;

import org.example.concurrency.dao.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private RedisTemplate<String, Student> redisTemplate;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Student> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public Student getStudent(Integer id) {
        return redisTemplate.opsForValue().get(String.valueOf(id));
    }

    public void setStudent(Student student) {
        redisTemplate.opsForValue().set(String.valueOf(student.getId()), student);
    }

}
