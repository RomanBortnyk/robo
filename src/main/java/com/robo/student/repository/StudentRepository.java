package com.robo.student.repository;

import com.robo.student.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, String> {

//    Optional<Student> findByStudentId(String id);

    List<Student> findAllBySchoolGrade(Byte grade);

    List<Student> findAll();

}
