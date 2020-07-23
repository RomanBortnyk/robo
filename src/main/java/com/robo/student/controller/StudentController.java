package com.robo.student.controller;

import com.robo.student.dto.DeleteDto;
import com.robo.student.dto.StatsDto;
import com.robo.student.dto.StudentDto;
import com.robo.student.dto.StudentsCountDto;
import com.robo.student.service.StudentService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/{id}")
    public StudentDto findById(@PathVariable String id){
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(@Valid @RequestBody StudentDto student){
        return studentService.save(student);
    }

    @GetMapping
    public StudentsCountDto getAllStudents(Sort sort){
        return studentService.getAll(sort);
    }

    @DeleteMapping("/{id}")
    public StudentDto deleteStudent(@PathVariable String id){
        return studentService.deleteById(id);
    }

    @GetMapping("/grade/{grade}")
    public StatsDto getStats(@PathVariable @Min(1) @Max(12) Byte grade,
                             @RequestParam(name = "stats") List<String> params){

        return studentService.getStats(grade, params);
    }

    @DeleteMapping
    public DeleteDto deleteAll(){
        return studentService.deleteAll();
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }

}
