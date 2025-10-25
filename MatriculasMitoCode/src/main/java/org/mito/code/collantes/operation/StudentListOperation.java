package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.StudentListResponse;
import org.mito.code.collantes.service.StudentListService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentListOperation {

    private final StudentListService studentListService;

    public ResponseEntity<StudentListResponse> listEstudiantes(){
        return studentListService.listar();
    }

}