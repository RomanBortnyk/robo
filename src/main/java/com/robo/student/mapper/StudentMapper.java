package com.robo.student.mapper;

import com.robo.student.dto.StudentDto;
import com.robo.student.model.Student;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toStudent(StudentDto studentDto) {

        Student student = new Student();
        student.setStudentId(studentDto.getStudentId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setSchoolGrade(studentDto.getSchoolGrade());
        student.setStudentsAverage(studentDto.getStudentsAverage());

        return student;
    }

    public StudentDto toStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setSchoolGrade(student.getSchoolGrade());
        studentDto.setStudentsAverage(student.getStudentsAverage());

        return studentDto;

    }
}
