package org.mito.code.collantes.operation;

import lombok.RequiredArgsConstructor;
import org.mito.code.collantes.model.CourseOperationResponse;
import org.mito.code.collantes.model.EnrollmentOperationResponse;
import org.mito.code.collantes.model.EnrollmentRequest;
import org.mito.code.collantes.service.EnrollmentService;
import org.mito.code.collantes.util.converter.ObjectConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Map;
import static org.mito.code.collantes.util.validator.ValidatorRequest.validateEnrollmentRequest;

@Component
@RequiredArgsConstructor
public class EnrollmentOperation {

    private final EnrollmentService enrollmentService;
    private final ObjectConverter objectConverter;

    public ResponseEntity<EnrollmentOperationResponse> saveEnrollment(EnrollmentRequest request) {
        Map<String, String> errors = validateEnrollmentRequest(request);
        if (!errors.isEmpty()) {
            EnrollmentOperationResponse errorResponse = EnrollmentOperationResponse.builder().data(null)
                    .notifications(objectConverter.notificationConverterMap(errors)).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
        return enrollmentService.createEnrollment(request);
    }

}