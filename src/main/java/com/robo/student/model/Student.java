package com.robo.student.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Student {

    @Id
    @Column(columnDefinition = "VARCHAR(8)")
    String studentId;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column
    LocalDate dateOfBirth;

    @Column
    @Min(1)
    @Max(12)
    Byte schoolGrade;

    @Column
    @Min(0)
    @Max(100)
    Byte studentsAverage;
}
