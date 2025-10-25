package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.StudentOperacionResponse;
import org.mito.code.collantes.model.StudentRequest;
import org.mito.code.collantes.service.StudentSaveService;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import static org.mito.code.collantes.util.validator.StudentValidatorRequest.validateEstudianteRequest;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StudentSaveOperation {

    private final StudentSaveService studentSaveService;
    private final ObjectConverter objectConverter;

    public ResponseEntity<StudentOperacionResponse> saveEstudiante (StudentRequest request) {
        Map<String, String> errors = validateEstudianteRequest(request);
        if (!errors.isEmpty()) {
            StudentOperacionResponse errorResponse = StudentOperacionResponse.builder().data(null)
                    .notifications(objectConverter.notificationConverterMap(errors)).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return studentSaveService.createStudent(request);
    }

    public ResponseEntity<StudentOperacionResponse> updateEstudiante (int id, StudentRequest request) {
        return studentSaveService.updateStudent(id, request);
    }

}