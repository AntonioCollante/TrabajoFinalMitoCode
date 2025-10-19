package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.CursoOperacionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CursoSaveOperation {

    public ResponseEntity<CursoOperacionResponse> saveCurso(CursoOperacionResponse curso){
        return null;
    }

}