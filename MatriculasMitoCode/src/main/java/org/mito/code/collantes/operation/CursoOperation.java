package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.CursoSingleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CursoOperation {

    public ResponseEntity<CursoSingleResponse> getCurso(Integer idCurso) {
        return null;
    }

}