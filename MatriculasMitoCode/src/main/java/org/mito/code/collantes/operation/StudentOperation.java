package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.StudentSingleResponse;
import org.mito.code.collantes.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentOperation {

    private final StudentService studentService;

    public ResponseEntity<StudentSingleResponse> getEstudiante(int id){
        return studentService.getEstudiante(id);
    }

}