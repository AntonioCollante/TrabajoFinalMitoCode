package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.EstudianteSingleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EstudianteOperation {

    public ResponseEntity<EstudianteSingleResponse> getEstudiante(int id){
        return null;
    }

}