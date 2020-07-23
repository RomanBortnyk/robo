package com.robo.student.exception;

public class StudentAlreadyPresentException extends RuntimeException {
    public StudentAlreadyPresentException(String s) {
        super(s);
    }
}
