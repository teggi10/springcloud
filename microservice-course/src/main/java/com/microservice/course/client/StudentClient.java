package com.microservice.course.client;

import com.microservice.course.controller.sto.StudentDTO;
import com.microservice.course.interceptor.FeignClientConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-student", url = "localhost:8090", configuration = FeignClientConfig.class)
public interface StudentClient {

    @GetMapping("/api/student/search-by-course/{courseId}")
    List<StudentDTO> findAllStudentByCourse(@PathVariable Long courseId);
}
