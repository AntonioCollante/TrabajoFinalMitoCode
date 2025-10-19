package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.EstudianteListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EstudianteListOperation {

    public ResponseEntity<EstudianteListResponse> listEstudiantes(){
        return null;
    }

}