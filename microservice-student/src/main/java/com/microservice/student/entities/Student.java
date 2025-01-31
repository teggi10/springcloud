package com.microservice.student.entities;

import com.thoughtworks.xstream.converters.basic.StringConverter;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private String email;

    @Lob
    @ToString.Exclude
    @Column(name = "file_data")
    private String fileData;
    

    @Column(name = "course_id")
    private Long courseId;
}
