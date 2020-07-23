package com.robo.student.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsDto {

    Byte grade;
    Integer average;
    Integer numStudents;
    @JsonFormat(pattern = ".##")
    Double stdDev;
}
