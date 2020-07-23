package com.robo.student.dto;

import com.robo.student.model.Student;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class StudentsCountDto {
    Integer totalStudents;
    List<StudentDto> students;
}
