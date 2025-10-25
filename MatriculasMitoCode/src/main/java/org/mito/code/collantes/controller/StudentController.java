package org.mito.code.collantes.controller;

import org.mito.code.collantes.delegate.StudentDelegate;
import org.mito.code.collantes.model.StudentListResponse;
import org.mito.code.collantes.model.StudentOperacionResponse;
import org.mito.code.collantes.model.StudentRequest;
import org.mito.code.collantes.model.StudentSingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    StudentDelegate alumnoDelegate;

    @PostMapping(value= "/createStudent", produces = {"application/json"})
    public ResponseEntity<StudentOperacionResponse> createStudent(
            @RequestBody StudentRequest request
    ) {
        return alumnoDelegate.saveStudent(request);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentSingleResponse> obtenerEstudiante(
            @PathVariable Integer id
    ) {
        return alumnoDelegate.getStudent(id);
    }

    @GetMapping("/listStudent")
    public ResponseEntity<StudentListResponse> listarEstudiantes() {
        return alumnoDelegate.listStudent();
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentOperacionResponse> actualizarEstudiante(
            @PathVariable Integer id,
            @RequestBody StudentRequest request
    ) {
        return alumnoDelegate.updateStudent(id, request);
    }

}
