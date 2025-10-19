package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.EstudianteOperacionResponse;
import org.mito.code.collantes.model.EstudianteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EstudianteSaveOperation {

    public ResponseEntity<EstudianteOperacionResponse> saveEstudiante (EstudianteRequest request) {
        return null;
    }

    public ResponseEntity<EstudianteOperacionResponse> updateEstudiante (int id, EstudianteRequest request) {
        return null;
    }

}