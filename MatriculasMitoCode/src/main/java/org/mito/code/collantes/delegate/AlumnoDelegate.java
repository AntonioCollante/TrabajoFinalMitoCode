package org.mito.code.collantes.delegate;

import org.mito.code.collantes.model.EstudianteListResponse;
import org.mito.code.collantes.model.EstudianteOperacionResponse;
import org.mito.code.collantes.model.EstudianteRequest;
import org.mito.code.collantes.model.EstudianteSingleResponse;
import org.mito.code.collantes.operation.EstudianteListOperation;
import org.mito.code.collantes.operation.EstudianteOperation;
import org.mito.code.collantes.operation.EstudianteSaveOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AlumnoDelegate {

    @Autowired
    @Qualifier("estudianteListOperation")
    EstudianteListOperation estudianteListOperation;

    @Autowired
    @Qualifier("estudianteSaveOperation")
    EstudianteSaveOperation estudianteSaveOperation;

    @Autowired
    @Qualifier("estudianteOperation")
    EstudianteOperation estudianteOperation;

    public ResponseEntity<EstudianteSingleResponse> getStudent(int id){
        return estudianteOperation.getEstudiante(id);
    }

    public ResponseEntity<EstudianteOperacionResponse> saveStudent (EstudianteRequest request) {
        return estudianteSaveOperation.saveEstudiante(request);
    }

    public ResponseEntity<EstudianteOperacionResponse> updateStudent (int id, EstudianteRequest request) {
        return estudianteSaveOperation.updateEstudiante(id, request);
    }

    public ResponseEntity<EstudianteListResponse> listStudent () {
        return estudianteListOperation.listEstudiantes();
    }

}