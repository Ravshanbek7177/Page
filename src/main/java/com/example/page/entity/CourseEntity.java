package com.example.page.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "duration_date")
    private Integer duration;
    @Column(name = "created_date")
    private LocalDate createdDate;
}
