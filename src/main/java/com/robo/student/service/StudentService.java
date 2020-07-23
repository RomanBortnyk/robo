package com.robo.student.service;

import com.robo.student.dto.DeleteDto;
import com.robo.student.dto.StatsDto;
import com.robo.student.dto.StudentDto;
import com.robo.student.dto.StudentsCountDto;
import com.robo.student.exception.StudentAlreadyPresentException;
import com.robo.student.exception.StudentNotFoundException;
import com.robo.student.mapper.StudentMapper;
import com.robo.student.model.Student;
import com.robo.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private static final String STANDARD_DEVIATION_PARAM = "dev";
    private static final String AVERAGE_PARAM = "avg";

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentDto findById(String id) {
        return studentMapper.toStudentDto(getByIdOrThrowEx(id));
    }

    private Student getByIdOrThrowEx(String id) {
        return getById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " was not found"));
    }

    private Optional<Student> getById(String id) {
        return studentRepository.findById(id);
    }

    public StudentsCountDto getAll(Sort sort) {

        Iterable<Student> all = studentRepository.findAll(sort);

        List<StudentDto> students = new ArrayList<>();

        all.forEach(student -> {
            StudentDto studentDto = studentMapper.toStudentDto(student);
            students.add(studentDto);
        });

        return StudentsCountDto.builder()
                .students(students)
                .totalStudents(students.size())
                .build();
    }

    @Transactional
    public StudentDto save(StudentDto studentDto) {

        Optional<Student> studentOptional = getById(studentDto.getStudentId());

        if (studentOptional.isPresent()) {
            throw new StudentAlreadyPresentException("Student with id " + studentDto.getStudentId() + " is already present");
        }

        Student student = studentMapper.toStudent(studentDto);
        studentRepository.save(student);

        studentDto.setCreatedAt(ZonedDateTime.now());
        return studentDto;
    }

    @Transactional
    public StudentDto deleteById(String id) {

        Student student = getByIdOrThrowEx(id);
        studentRepository.delete(student);

        StudentDto studentDto = studentMapper.toStudentDto(student);
        studentDto.setDeletedAt(ZonedDateTime.now());

        return studentDto;
    }

    public StatsDto getStats(Byte grade, List<String> params) {

        List<Student> students = studentRepository.findAllBySchoolGrade(grade);

        StatsDto.StatsDtoBuilder statsDtoBuilder = StatsDto.builder()
                .numStudents(students.size())
                .grade(grade);

        if (students.isEmpty()) {
            return statsDtoBuilder.build();
        }

        if (params.contains(AVERAGE_PARAM)) {
            statsDtoBuilder.average(computeAverage(students));
        }

        if (params.contains(STANDARD_DEVIATION_PARAM)) {
            BigDecimal dev = BigDecimal.valueOf(computeDeviation(students)).setScale(1, RoundingMode.CEILING);
            statsDtoBuilder.stdDev(dev.doubleValue());
        }

        return statsDtoBuilder.build();
    }

    private double computeDeviation(List<Student> students) {

        double standardDeviation = 0.0;

        double mean = computeAverage(students);

        for (Student student : students) {
            standardDeviation += Math.pow(student.getStudentsAverage() - mean, 2);
        }

        return Math.sqrt(standardDeviation / students.size());
    }

    private Integer computeAverage(List<Student> students) {

        if (students.isEmpty()) {
            return 0;
        }

        int sum = students.stream()
                .mapToInt(Student::getStudentsAverage)
                .sum();

        return sum / students.size();
    }

    @Transactional
    public DeleteDto deleteAll() {
        studentRepository.deleteAll();
        return new DeleteDto();
    }
}
