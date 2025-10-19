package org.mito.code.collantes.controller;

import org.mito.code.collantes.delegate.AlumnoDelegate;
import org.mito.code.collantes.model.EstudianteListResponse;
import org.mito.code.collantes.model.EstudianteOperacionResponse;
import org.mito.code.collantes.model.EstudianteRequest;
import org.mito.code.collantes.model.EstudianteSingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EstudianteController {

    @Autowired
    AlumnoDelegate alumnoDelegate;

    @PostMapping(value= "/createStudent", produces = {"application/json"})
    public ResponseEntity<EstudianteOperacionResponse> createStudent(
            @RequestBody EstudianteRequest request
    ) {
        return alumnoDelegate.saveStudent(request);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<EstudianteSingleResponse> obtenerEstudiante(
            @PathVariable Integer id
    ) {
        return alumnoDelegate.getStudent(id);
    }

    @GetMapping("/listStudent")
    public ResponseEntity<EstudianteListResponse> listarEstudiantes() {
        return alumnoDelegate.listStudent();
    }

    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteOperacionResponse> actualizarEstudiante(
            @PathVariable Integer id,
            @RequestBody EstudianteRequest request
    ) {
        return alumnoDelegate.updateStudent(id, request);
    }

}
