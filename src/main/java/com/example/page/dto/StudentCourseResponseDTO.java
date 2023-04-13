package com.example.page.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseResponseDTO {
    private Integer id;
    private CourseResponseDTO course;
    private StudentResponseDTO student;
    private Integer mark;
    private LocalDate createdDate;
}
