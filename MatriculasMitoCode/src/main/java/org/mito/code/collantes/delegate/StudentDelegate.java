package org.mito.code.collantes.delegate;

import org.mito.code.collantes.model.StudentListResponse;
import org.mito.code.collantes.model.StudentOperacionResponse;
import org.mito.code.collantes.model.StudentRequest;
import org.mito.code.collantes.model.StudentSingleResponse;
import org.mito.code.collantes.operation.StudentListOperation;
import org.mito.code.collantes.operation.StudentOperation;
import org.mito.code.collantes.operation.StudentSaveOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentDelegate {

    @Autowired
    @Qualifier("studentListOperation")
    StudentListOperation estudianteListOperation;

    @Autowired
    @Qualifier("studentSaveOperation")
    StudentSaveOperation estudianteSaveOperation;

    @Autowired
    @Qualifier("studentOperation")
    StudentOperation estudianteOperation;

    public ResponseEntity<StudentSingleResponse> getStudent(int id){
        return estudianteOperation.getEstudiante(id);
    }

    public ResponseEntity<StudentOperacionResponse> saveStudent(StudentRequest request) {
        return estudianteSaveOperation.saveEstudiante(request);
    }

    public ResponseEntity<StudentOperacionResponse> updateStudent (int id, StudentRequest request) {
        return estudianteSaveOperation.updateEstudiante(id, request);
    }

    public ResponseEntity<StudentListResponse> listStudent () {
        return estudianteListOperation.listEstudiantes();
    }

}