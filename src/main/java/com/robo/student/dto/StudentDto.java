package com.robo.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.robo.student.validation.ValidStudentId;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    @ValidStudentId(message = "Student id must be up to 8 digit string")
    String studentId;

    @NotEmpty(message = "First name should not be null or empty")
    String firstName;

    @NotEmpty(message = "Last name should not be null or empty")
    String lastName;

    @JsonFormat(pattern = "d/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate dateOfBirth;

    @Min(value = 1, message = "Grade should be between 1 and 12")
    @Max(value = 12, message = "Grade should be between 1 and 12")
    Byte schoolGrade;

    @Min(value = 0, message = "Average should be between 0 and 100")
    @Max(value = 100, message = "Average should be between 0 and 100")
    Byte studentsAverage;

    ZonedDateTime createdAt;

    ZonedDateTime deletedAt;
}
