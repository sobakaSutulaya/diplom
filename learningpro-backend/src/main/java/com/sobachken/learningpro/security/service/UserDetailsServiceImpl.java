package com.sobachken.learningpro.security.service;

import com.sobachken.learningpro.model.Student;
import com.sobachken.learningpro.model.Teacher;
import com.sobachken.learningpro.repository.StudentRepository;
import com.sobachken.learningpro.repository.TeacherRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public UserDetailsServiceImpl(TeacherRepository teacherRepository,
                                  StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByLogin(s).orElse(null);
        if (teacher != null) {
            return new User(teacher.getLogin(), teacher.getPassword(), getAuthorities(teacher.getClass()));
        }

        Student student = studentRepository.findByCardNumber(s).orElse(null);
        if (student != null) {
            return new User(student.getCardNumber(), student.getPassword(), getAuthorities(student.getClass()));
        }
        throw new UsernameNotFoundException("No user found by : " + s);
    }

    private Set getAuthorities(Class<?> clazz) {
        return Collections.singleton("ROLE_".concat(clazz.getSimpleName().toUpperCase()));
    }
}
