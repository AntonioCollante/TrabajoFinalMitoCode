package org.mito.code.collantes.controller;

import org.mito.code.collantes.delegate.CourseDelegate;
import org.mito.code.collantes.model.CourseListResponse;
import org.mito.code.collantes.model.CourseOperacionResponse;
import org.mito.code.collantes.model.CourseRequest;
import org.mito.code.collantes.model.CourseSingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    CourseDelegate cursoDelegate;

    @PostMapping(value= "/createCourses", produces = {"application/json"})
    public ResponseEntity<CourseOperacionResponse> crearCurso(
            @RequestBody CourseRequest request
    ) {
        return cursoDelegate.createCourses(request);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseSingleResponse> obtenerCurso(
            @PathVariable Integer id
    ) {
        return cursoDelegate.getCourses(id);
    }

    @GetMapping("/courses")
    public ResponseEntity<CourseListResponse> listarCursos() {
        return cursoDelegate.listCourses();
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseOperacionResponse> updateCourses(
            @PathVariable Integer id,
            @RequestBody CourseRequest request
    ) {
        return cursoDelegate.updateCourses(id, request);
    }

}