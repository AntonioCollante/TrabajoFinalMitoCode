package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.CourseOperationResponse;
import org.mito.code.collantes.model.CourseRequest;
import org.mito.code.collantes.service.CourseSaveService;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Map;
import static org.mito.code.collantes.util.validator.ValidatorRequest.validateCursoRequest;

@Component
@RequiredArgsConstructor
public class CourseSaveOperation {

    private final CourseSaveService courseSaveService;
    private final ObjectConverter objectConverter;

    public ResponseEntity<CourseOperationResponse> saveCurso(CourseRequest request){
        Map<String, String> errors = validateCursoRequest(request);
        if (!errors.isEmpty()) {
            CourseOperationResponse errorResponse = CourseOperationResponse.builder().data(null)
                    .notifications(objectConverter.notificationConverterMap(errors)).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return courseSaveService.createCourse(request);
    }

    public ResponseEntity<CourseOperationResponse> updateCurso(int id, CourseRequest request){
        Map<String, String> errors = validateCursoRequest(request);
        if (!errors.isEmpty()) {
            CourseOperationResponse errorResponse = CourseOperationResponse.builder().data(null)
                    .notifications(objectConverter.notificationConverterMap(errors)).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return courseSaveService.updateCourse(id, request);
    }

}